package com.example.userjwtvalidation.auth;

import com.example.userjwtvalidation.user.RoleTypes;

public record SignUpRequest (String username, String email, String password) {
}
