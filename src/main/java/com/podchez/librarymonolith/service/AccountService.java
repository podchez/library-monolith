package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.AccountRequestDto;
import com.podchez.librarymonolith.dto.AccountResponseDto;

import java.util.List;

public interface AccountService {

    List<AccountResponseDto> findAll();

    AccountResponseDto findById(Long id);

    AccountResponseDto findByEmail(String email);

    AccountResponseDto save(AccountRequestDto accReqDto);

    AccountResponseDto update(Long id, AccountRequestDto accReqDto);

    void deleteById(Long id);
}
