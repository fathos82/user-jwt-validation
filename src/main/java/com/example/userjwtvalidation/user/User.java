package com.example.userjwtvalidation.user;

import com.example.userjwtvalidation.publication.Publication;
import jakarta.persistence.*;

import java.util.*;

@Table(name = "\"users\"")
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    private String username;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Publication> publications = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {
    }

    public User(String username,String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Publication> getPublications() {
        return publications;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}
