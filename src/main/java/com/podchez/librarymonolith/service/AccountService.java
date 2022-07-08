package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Long id);

    Account findByUsername(String username);

    Account findByEmail(String email);

    void save(Account account);

    void update(Long id, Account account);

    void delete(Long id);
}
