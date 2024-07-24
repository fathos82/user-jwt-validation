package com.example.userjwtvalidation.user;

import com.example.userjwtvalidation.user.request.SetRoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;
    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<UserResponse>> listAll() {
        List<UserResponse> userResponseList = userService.listAll();
        if (userResponseList.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userResponseList);
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(new UserResponse(user));
    }

    @PutMapping("/assign-role")
    public ResponseEntity<Void> assignRolesToUser(@RequestBody SetRoleRequest roleRequest) {
        userService.assignRoles(roleRequest);
        return ResponseEntity.ok().build();
    }

}
