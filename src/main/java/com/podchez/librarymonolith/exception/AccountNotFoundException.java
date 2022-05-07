package com.podchez.librarymonolith.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("There is no account with ID = " + id + " in the database.");
    }

    public AccountNotFoundException(String email) {
        super("There is no account with email '" + email + "' in the database.");
    }
}
