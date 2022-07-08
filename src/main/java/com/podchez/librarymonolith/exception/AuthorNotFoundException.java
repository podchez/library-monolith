package com.podchez.librarymonolith.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
