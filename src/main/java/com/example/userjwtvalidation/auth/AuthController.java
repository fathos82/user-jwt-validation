package com.example.userjwtvalidation.auth;

import com.example.userjwtvalidation.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("singup")
    public ResponseEntity<Void> registerUser(@RequestBody SignUpRequest signUpRequest){
        User user = authService.registerUser(signUpRequest);
        // Todo: Analisar Retorno de erro ao criar usuario!
        return ResponseEntity.ok().build();

    }
    @PostMapping("login")
        public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@RequestBody AuthRequest authRequest) {

        return ResponseEntity.ok(authService.authenticate(authRequest));

    }
}
