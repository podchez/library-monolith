package com.podchez.librarymonolith.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("There is no book with ID = " + id + " in the database.");
    }
}
