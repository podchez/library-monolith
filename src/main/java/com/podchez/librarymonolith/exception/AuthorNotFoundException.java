package com.podchez.librarymonolith.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long id) {
        super("There is no author with ID = " + id + " in the database.");
    }
}
