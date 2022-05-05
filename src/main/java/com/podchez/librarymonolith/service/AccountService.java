package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.AccountDto;

import java.util.List;

public interface AccountService {

    List<AccountDto> findAll();

    AccountDto findById(Long id);

    AccountDto save(AccountDto accountDto);

    AccountDto update(Long id, AccountDto accountDto);

    void deleteById(Long id);
}
