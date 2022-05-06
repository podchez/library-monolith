package com.podchez.librarymonolith.exception;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(String email) {
        super("Account with email '" + email + "' already exists.");
    }
}
