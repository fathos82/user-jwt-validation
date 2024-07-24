package com.example.userjwtvalidation.publication.request;

public record EditPublicationRequest(Long publicationId, String title, String content) {
}
