package com.podchez.librarymonolith.exception;

public class GenreAlreadyExistsException extends RuntimeException {
    public GenreAlreadyExistsException(String name) {
        super("Genre with name '" + name + "' already exists.");
    }
}
