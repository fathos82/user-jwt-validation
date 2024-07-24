package com.example.userjwtvalidation.auth;

import org.springframework.web.bind.annotation.RestController;


public record AuthRequest (String username, String password) {
}
