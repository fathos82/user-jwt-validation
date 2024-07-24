package com.example.userjwtvalidation.security;

import com.example.userjwtvalidation.user.RoleTypes;
import com.example.userjwtvalidation.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SecurityUser implements UserDetails {
    private final User user;


    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
                    for (RoleTypes roleType : role.getRoleType().getAllAuthoritiesRole()) {
                        authorities.add(new SimpleGrantedAuthority(roleType.name()));
                    }
                }
        );
        return Collections.unmodifiableSet(authorities);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
