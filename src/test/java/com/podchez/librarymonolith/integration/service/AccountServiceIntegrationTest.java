package com.podchez.librarymonolith.integration.service;

import com.podchez.librarymonolith.integration.annotation.IntegrationTest;
import com.podchez.librarymonolith.model.Account;
import com.podchez.librarymonolith.repository.AccountRepository;
import com.podchez.librarymonolith.repository.RoleRepository;
import com.podchez.librarymonolith.service.impl.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestConstructor;

import javax.validation.Validator;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class AccountServiceIntegrationTest {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final Validator validator;
    private final PasswordEncoder passwordEncoder;
    private final AccountServiceImpl accountService;

    @Test
    void shouldFindAccount_whenAccountExistsById() {
        // given
        Long id = 4L;
        String username = "dummyUsername";
        String email = "dummy@dummy.com";
        String passwordNotEncoded = "dummyPassword";
        Account account = new Account(id, username, email, passwordNotEncoded,
                null, null, null, null);
        accountService.save(account);

        // when
        assertDoesNotThrow(() -> accountService.findById(id));
        Account actual = accountService.findById(id);

        // then
        assertThat(actual.getId()).isEqualTo(id);
        assertThat(actual).isEqualTo(account);
        assertThat(passwordEncoder.matches(passwordNotEncoded, actual.getPassword())).isTrue();
    }
}
