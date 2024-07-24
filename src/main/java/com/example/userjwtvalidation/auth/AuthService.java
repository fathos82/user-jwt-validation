package com.example.userjwtvalidation.auth;

import com.example.userjwtvalidation.security.JwtService;
import com.example.userjwtvalidation.user.User;
import com.example.userjwtvalidation.user.UserRepository;
import com.example.userjwtvalidation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    public JwtAuthenticationResponse authenticate(AuthRequest authRequest){
        String username = authRequest.username();
        String password = authRequest.password();
        // Autenticar:
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticationResult = authenticationManager.authenticate(authentication);

        // Caso Esteja Autenticado com sucesso:
            return new JwtAuthenticationResponse(jwtService.generateToken(authenticationResult.getName()),"POR ENQUANTO");

    }

    public User registerUser(SignUpRequest signUpRequest) {
        String passwordEncoded = passwordEncoder.encode(signUpRequest.password());
        return  userRepository.save(new User(signUpRequest.username(), signUpRequest.email(), passwordEncoded));

    }
    public User getAuthorizedUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //Todo: fix This, encapsulate in another class to get Authorized user, and throw errors
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with username: " + username));

    }
}
