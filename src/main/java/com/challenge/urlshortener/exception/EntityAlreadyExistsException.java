package com.challenge.urlshortener.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(final String message) {
        super(message);
    }
}
