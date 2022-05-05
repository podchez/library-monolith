package com.podchez.librarymonolith.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Integer id) {
        super("There is no genre with ID = " + id + " in the database.");
    }
}
