package com.example.userjwtvalidation.publication.response;

import com.example.userjwtvalidation.publication.Publication;
import com.example.userjwtvalidation.user.UserResponse;

public record AdminPublicationResponse(Long id, String title, String content, UserResponse userResponse) {
    public AdminPublicationResponse(Publication message){
        this(message.getId(), message.getTitle(), message.getContent(), new UserResponse(message.getUser()));
    }
}
