package com.example.userjwtvalidation.auth;

public record JwtAuthenticationResponse(String token, String expiresIn) {
}
