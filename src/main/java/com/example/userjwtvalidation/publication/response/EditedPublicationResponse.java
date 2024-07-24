package com.example.userjwtvalidation.publication.response;

import com.example.userjwtvalidation.publication.Publication;
import com.example.userjwtvalidation.user.UserResponse;


public record EditedPublicationResponse(Long publicationId, String title, String content) {
    public EditedPublicationResponse(Publication publication) {
        this(publication.getId(), publication.getTitle(), publication.getContent());
    }
}
