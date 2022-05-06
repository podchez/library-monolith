package com.podchez.librarymonolith.exception;

public class AuthorAlreadyExistsException extends RuntimeException {
    public AuthorAlreadyExistsException(String fullName) {
        super("Author with full name '" + fullName + "' already exists.");
    }
}
