package com.podchez.librarymonolith.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Integer id) {
        super("There is no role with ID = " + id + " in the database.");
    }
}
