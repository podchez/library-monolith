package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.dto.AccountRequestDto;
import com.podchez.librarymonolith.dto.AccountResponseDto;
import com.podchez.librarymonolith.dto.mapper.AccountMapper;
import com.podchez.librarymonolith.entity.Account;
import com.podchez.librarymonolith.entity.Role;
import com.podchez.librarymonolith.exception.AccountAlreadyExistsException;
import com.podchez.librarymonolith.exception.AccountNotFoundException;
import com.podchez.librarymonolith.exception.RoleNotFoundException;
import com.podchez.librarymonolith.repository.AccountRepository;
import com.podchez.librarymonolith.repository.RoleRepository;
import com.podchez.librarymonolith.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<AccountResponseDto> findAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDto findById(Long id) {
        return accountMapper.toRespDto(accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id)));
    }

    @Override
    public AccountResponseDto findByEmail(String email) {
        return accountMapper.toRespDto(accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException(email)));
    }

    @Override
    public AccountResponseDto save(AccountRequestDto accReqDto) {
        String accountEmail = accReqDto.getEmail();
        if (accountRepository.findByEmail(accountEmail).isPresent()) {
            throw new AccountAlreadyExistsException(accountEmail);
        }

        Account account = accountMapper.toEntity(accReqDto);
        account.setRoles(getRolesFromAccReqDto(accReqDto));

        Account savedAccount = accountRepository.save(account);
        return accountMapper.toRespDto(savedAccount);
    }

    @Override
    public AccountResponseDto update(Long id, AccountRequestDto accReqDto) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException(id);
        }

        String accountEmail = accReqDto.getEmail();
        Optional<Account> accountWithSameEmail = accountRepository.findByEmail(accountEmail);
        if(accountWithSameEmail.isPresent() &&
                !accountWithSameEmail.get().getId().equals(id)) {
            throw new AccountAlreadyExistsException(accountEmail);
        }

        Account account = accountMapper.toEntity(accReqDto);
        account.setRoles(getRolesFromAccReqDto(accReqDto));
        account.setId(id); // to avoid saving

        Account updatedAccount = accountRepository.save(account);
        return accountMapper.toRespDto(updatedAccount);
    }

    @Override
    public void deleteById(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException(id);
        }
        accountRepository.deleteById(id);
    }

    // Helper method
    private Set<Role> getRolesFromAccReqDto(AccountRequestDto accReqDto) {
        Set<Role> accountRoles = new HashSet<>();
        for (String accRoleName : accReqDto.getRoleNames()) {
            accountRoles.add(roleRepository.findByName(accRoleName)
                    .orElseThrow(() -> new RoleNotFoundException(accRoleName)));
        }
        return accountRoles;
    }
}
