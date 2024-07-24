package com.example.userjwtvalidation.user;

import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", length = 20, nullable = false)
    private RoleTypes roleType;

    public Role(RoleTypes role) {
        roleType = role;
    }

    public Role() {

    }

    public Long getId() {
        return id;
    }

    public RoleTypes getRoleType() {
        return roleType;
    }
    // Getters and Setters
}
