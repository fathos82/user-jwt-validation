package com.example.userjwtvalidation.publication.exceptions;

public class PublicationNotFound extends RuntimeException{
    public PublicationNotFound(Long id) {
        super("Publication with "+id+" not found");
    }
}
