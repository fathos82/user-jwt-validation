package com.example.userjwtvalidation.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
     User save(User entity);

    Optional<User> findByUsername(String username);

    @Override
    List<User> findAll();
}
