package com.challenge.urlshortener.exception;

public class ObjectAlreadyExistsException extends RuntimeException {
    public ObjectAlreadyExistsException(final String message) {
        super(message);
    }
}
