package com.podchez.librarymonolith.repository;

import com.podchez.librarymonolith.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void shouldFindAuthor_whenAuthorExistsByFullName() {
        // given
        String fullName = "Dummy Dummy";
        Author author = new Author(null, fullName, null);
        authorRepository.save(author);

        // when
        Optional<Author> actual = authorRepository.findByFullName(fullName);

        // then
        assertThat(actual).isPresent();
        actual.ifPresent(actualAuthor ->
                assertThat(actualAuthor.getFullName()).isEqualTo(fullName));
    }

    @Test
    void shouldNotFindAuthor_whenAuthorDoesNotExistByFullName() {
        // given
        String fullName = "Dummy Dummy";

        // when
        Optional<Author> actual = authorRepository.findByFullName(fullName);

        // then
        assertThat(actual).isEmpty();
    }

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
    }
}