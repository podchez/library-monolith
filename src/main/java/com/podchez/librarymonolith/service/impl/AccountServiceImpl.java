package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.model.Account;
import com.podchez.librarymonolith.exception.AccountAlreadyExistsException;
import com.podchez.librarymonolith.exception.AccountNotFoundException;
import com.podchez.librarymonolith.repository.AccountRepository;
import com.podchez.librarymonolith.repository.RoleRepository;
import com.podchez.librarymonolith.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional(readOnly = true)
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final Validator validator;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository,
                              Validator validator, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Account> findAll() {
        log.info("IN findAll");
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        log.info("IN findById - id: {}", id);
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("There is no account with ID " + id + " in the DB"));
    }

    @Override
    public Account findByUsername(String username) {
        log.info("IN findByUsername - username: {}", username);
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException("There is no account with username '" + username + "' in the DB"));
    }

    @Override
    public Account findByEmail(String email) {
        log.info("IN findByEmail - email: {}", email);
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException("There is no account with email '" + email + "' in the DB"));
    }

    @Override
    @Transactional
    public void save(Account account) {
        validate(account);

        String username = account.getUsername();
        if (accountRepository.findByUsername(username).isPresent()) {
            throw new AccountAlreadyExistsException("Account with username '" + username + "' already exists");
        }

        String email = account.getEmail();
        if (accountRepository.findByEmail(email).isPresent()) {
            throw new AccountAlreadyExistsException("Account with email '" + email + "' already exists");
        }

        account.setIsEnabled(true);
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        account.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER").get()));
        accountRepository.save(account);
        log.info("IN save - account with username: {} and email: {} saved", username, email);
    }

    @Override
    @Transactional
    public void update(Long id, Account account) {
        validate(account);

        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException("There is no account with ID " + id + " in the DB");
        }

        String username = account.getUsername();
        accountRepository.findByUsername(username).ifPresent(accountWithSameUsername -> {
            if (!accountWithSameUsername.getId().equals(id)) {
                throw new AccountNotFoundException("Account with username '" + username + "' already exists");
            }
        });

        String email = account.getEmail();
        accountRepository.findByEmail(email).ifPresent(accountWithSameEmail -> {
            if (!accountWithSameEmail.getId().equals(id)) {
                throw new AccountNotFoundException("Account with email '" + email + "' already exists");
            }
        });

        account.setUpdatedAt(LocalDateTime.now());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setId(id);
        accountRepository.save(account);
        log.info("IN update - account with id: {} updated", id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new AccountNotFoundException("There is no account with ID " + id + " in the DB");
        }

        accountRepository.deleteById(id);
        log.info("IN delete - account with id: {} deleted", id);
    }

    private void validate(Account account) {
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Account> violation : violations) {
                sb.append(violation.getMessage() + "; ");
            }
            throw new ConstraintViolationException(sb.toString(), violations);
        }
    }
}
