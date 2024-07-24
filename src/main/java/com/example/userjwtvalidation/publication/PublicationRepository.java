package com.example.userjwtvalidation.publication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    @Override
    <S extends Publication> S save(S entity);
    Optional<List<Publication>> findByUserId(UUID id);
    Optional<Publication> findPublicationById(Long id);
    @Override
    Page<Publication> findAll(Pageable pageable);
}
