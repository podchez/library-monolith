package com.podchez.librarymonolith.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long id) {
        super("There is no author with ID = " + id + " in the database.");
    }

    public AuthorNotFoundException(String fullName) {
        super("There is no author with full name '" + fullName + "' in the database.");
    }
}
