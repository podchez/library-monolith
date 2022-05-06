package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.dto.AccountDto;
import com.podchez.librarymonolith.dto.mapper.AccountMapper;
import com.podchez.librarymonolith.entity.Account;
import com.podchez.librarymonolith.exception.AccountAlreadyExistsException;
import com.podchez.librarymonolith.exception.AccountNotFoundException;
import com.podchez.librarymonolith.repository.AccountRepository;
import com.podchez.librarymonolith.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Long id) {
        return accountMapper.toDto(accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id)));
    }

    @Override
    public AccountDto save(AccountDto accountDto) {
        String accountEmail = accountDto.getEmail();
        if (accountRepository.findByEmail(accountEmail).isPresent()) {
            throw new AccountAlreadyExistsException(accountEmail);
        }

        accountDto.setId(null); // to avoid updating
        Account savedAccount = accountRepository.save(accountMapper.toEntity(accountDto));
        return accountMapper.toDto(savedAccount);
    }

    @Override
    public AccountDto update(Long id, AccountDto accountDto) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException(id);
        }

        String accountEmail = accountDto.getEmail();
        Optional<Account> accountWithSameEmail = accountRepository.findByEmail(accountEmail);
        if(accountWithSameEmail.isPresent() &&
                !accountWithSameEmail.get().getId().equals(id)) {
            throw new AccountAlreadyExistsException(accountEmail);
        }

        accountDto.setId(id); // accountDto should have correct id
        Account updatedAccount = accountRepository.save(accountMapper.toEntity(accountDto));
        return accountMapper.toDto(updatedAccount);
    }

    // TODO: add changePassword method

    @Override
    public void deleteById(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException(id);
        }
        accountRepository.deleteById(id);
    }
}
