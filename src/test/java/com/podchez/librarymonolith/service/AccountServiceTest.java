package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.model.Account;
import com.podchez.librarymonolith.repository.AccountRepository;
import com.podchez.librarymonolith.repository.RoleRepository;
import com.podchez.librarymonolith.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Validator;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private static final Long ACCOUNT_ID = 1L;
    private static final String ACCOUNT_USERNAME = "username";
    private static final String ACCOUNT_EMAIL = "email@email.com";
    private static final String ACCOUNT_PASSWORD = "password";
    private static final Account ACCOUNT = new Account(ACCOUNT_ID, ACCOUNT_USERNAME, ACCOUNT_EMAIL, ACCOUNT_PASSWORD,
            null, null, null, null);

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private Validator validator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void shouldFindAccounts_whenAccountsExist() {
        // given
        List<Account> accounts = List.of(ACCOUNT);
        doReturn(accounts)
                .when(accountRepository).findAll();

        // when
        List<Account> actual = accountService.findAll();

        // then
        assertThat(actual).isEqualTo(accounts);
        verifyNoMoreInteractions(roleRepository, validator, passwordEncoder);
    }

    @Test
    void shouldFindAccount_whenAccountExistsById() {
        // given
        doReturn(Optional.of(ACCOUNT))
                .when(accountRepository).findById(ACCOUNT_ID);

        // when
        assertDoesNotThrow(() -> accountService.findById(ACCOUNT_ID));
        Account actual = accountService.findById(ACCOUNT_ID);

        // then
        assertThat(actual.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(actual).isEqualTo(ACCOUNT);
        verifyNoMoreInteractions(roleRepository, validator, passwordEncoder);
    }

    @Test
    void shouldFindAccount_whenAccountExistsByUsername() {
        // given
        doReturn(Optional.of(ACCOUNT))
                .when(accountRepository).findByUsername(ACCOUNT_USERNAME);

        // when
        assertDoesNotThrow(() -> accountService.findByUsername(ACCOUNT_USERNAME));
        Account actual = accountService.findByUsername(ACCOUNT_USERNAME);

        // then
        assertThat(actual.getUsername()).isEqualTo(ACCOUNT_USERNAME);
        assertThat(actual).isEqualTo(ACCOUNT);
        verifyNoMoreInteractions(roleRepository, validator, passwordEncoder);
    }

    @Test
    void shouldFindAccount_whenAccountExistsByEmail() {
        // given
        doReturn(Optional.of(ACCOUNT))
                .when(accountRepository).findByEmail(ACCOUNT_EMAIL);

        // when
        assertDoesNotThrow(() -> accountService.findByEmail(ACCOUNT_EMAIL));
        Account actual = accountService.findByEmail(ACCOUNT_EMAIL);

        // then
        assertThat(actual.getEmail()).isEqualTo(ACCOUNT_EMAIL);
        assertThat(actual).isEqualTo(ACCOUNT);
        verifyNoMoreInteractions(roleRepository, validator, passwordEncoder);
    }
}