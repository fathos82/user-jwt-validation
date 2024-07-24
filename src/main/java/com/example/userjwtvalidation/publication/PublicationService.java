package com.example.userjwtvalidation.publication;

import com.example.userjwtvalidation.publication.exceptions.PublicationNotFound;
import com.example.userjwtvalidation.publication.request.CreatePublicationRequest;
import com.example.userjwtvalidation.publication.request.EditPublicationRequest;
import com.example.userjwtvalidation.user.User;
import com.example.userjwtvalidation.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PublicationService {
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    UserService userService;

    public Publication createPublication(CreatePublicationRequest publicationRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        Publication message = new Publication(publicationRequest.title(), publicationRequest.content(), user);
        return publicationRepository.save(message);
    }

    public List<Publication> listPublicationByUserId(UUID id) {
        // Todo: Mudar o retorno de erro
        return publicationRepository.findByUserId(id).orElseThrow(() -> new EntityNotFoundException("Publication not found"));
    }


    public Page<Publication> findAll(Pageable page) {
        return publicationRepository.findAll(page);
    }

    @Transactional
    public Publication updatePublication(EditPublicationRequest editPublicationRequest) {
        Publication publication = publicationRepository.findPublicationById(editPublicationRequest.publicationId())
                .orElseThrow(() -> new PublicationNotFound(editPublicationRequest.publicationId()));
        if (editPublicationRequest.title() != null) {
            publication.setTitle(editPublicationRequest.title());
        }
        if (editPublicationRequest.content() != null) {
            publication.setContent(editPublicationRequest.content());
        }
        return publicationRepository.save(publication);
    }
}
