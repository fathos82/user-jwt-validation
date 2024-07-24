package com.example.userjwtvalidation.user;

import java.util.*;

public enum RoleTypes{
    ROLE_READER("READER"),
    ROLE_WRITER("WRITER"),
    ROLE_ADMIN("ADMIN");

    private final String role;

    RoleTypes(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public Set<RoleTypes> getAllAuthoritiesRole() {
        return switch (this){
            case ROLE_ADMIN -> Set.of(ROLE_ADMIN, ROLE_READER, ROLE_WRITER);
            case ROLE_WRITER -> Set.of(ROLE_WRITER, ROLE_READER);
            case ROLE_READER -> Set.of(ROLE_READER);
        };
    }
}
