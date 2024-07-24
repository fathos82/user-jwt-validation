package com.example.userjwtvalidation.publication.response;

import com.example.userjwtvalidation.publication.Publication;
import com.example.userjwtvalidation.user.UserResponse;

public record PublicationResponse(Long publicationId, String title, String content, UserResponse user) {
    public PublicationResponse(Publication publication) {
        this(publication.getId(), publication.getTitle(), publication.getContent(), new UserResponse(publication.getUser()));
    }
}
