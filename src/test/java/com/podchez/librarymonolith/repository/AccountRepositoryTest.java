package com.podchez.librarymonolith.repository;

import com.podchez.librarymonolith.model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void shouldFindAccount_whenAccountExistsByEmail() {
        // given
        String username = "dummyUsername";
        String email = "dummy@dummy.com";
        String password = "dummyPassword";
        Account account = Account.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();

        accountRepository.save(account);

        // when
        Optional<Account> actual = accountRepository.findByEmail(email);

        // then
        assertThat(actual).isPresent();
        actual.ifPresent(actualAccount ->
                assertThat(actualAccount.getEmail()).isEqualTo(email));
    }

    @Test
    void shouldNotFindAccount_whenAccountDoesNotExistByEmail() {
        // given
        String email = "dummy@dummy.com";

        // when
        Optional<Account> actual = accountRepository.findByEmail(email);

        // then
        assertThat(actual).isEmpty();
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }
}