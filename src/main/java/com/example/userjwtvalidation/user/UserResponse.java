package com.example.userjwtvalidation.user;

import java.util.UUID;

public record UserResponse(UUID userId, String username) {
    public UserResponse(User user) {
        this(user.getId(), user.getUsername());
    }
}
