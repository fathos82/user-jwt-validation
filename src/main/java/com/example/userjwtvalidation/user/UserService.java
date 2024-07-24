package com.example.userjwtvalidation.user;

import com.example.userjwtvalidation.auth.AuthService;
import com.example.userjwtvalidation.user.exceptions.RoleAssignmentException;
import com.example.userjwtvalidation.user.exceptions.RoleNotFoundException;
import com.example.userjwtvalidation.user.request.SetRoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.userjwtvalidation.user.RoleTypes.ROLE_ADMIN;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired AuthService authService;
    public List<UserResponse> listAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponse::new).toList();
    }

    public void assignRoles(SetRoleRequest roleRequest ){
        if(roleRequest.role().equals(ROLE_ADMIN)) throw new RoleAssignmentException("Only admins can assign ROLE_ADMIN");
        User user = authService.getAuthorizedUser();
        Role role = roleRepository.findByRoleType(roleRequest.role())
                .orElseThrow(()->new RoleNotFoundException("There's not this role: "+roleRequest.role()));
        user.addRole(role);
        userRepository.save(user);
    }

    public User findByUsername(String username) { // Throw ERROR
        return  userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Can not found a user with username: "+username));
    }
}
