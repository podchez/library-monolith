package com.podchez.librarymonolith.repository;

import com.podchez.librarymonolith.model.Author;
import com.podchez.librarymonolith.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void shouldFindBooks_whenBooksExistByTitle() {
        // given
        String title = "Dummy";
        Author author1 = new Author(null, "authorFullName1", null);
        Author author2 = new Author(null, "authorFullName2", null);
        authorRepository.saveAll(List.of(author1, author2));

        Book book1 = new Book(null, title, 100, 1000L, true, author1);
        Book book2 = new Book(null, title, 200, 2000L, true, author2);
        bookRepository.saveAll(List.of(book1, book2));

        // when
        List<Book> actual = bookRepository.findAllByTitleIgnoreCaseContaining(title);

        // then
        int expectedSize = 2;
        assertThat(actual).hasSize(expectedSize);
    }

    @Test
    void shouldNotFindBooks_whenBooksDoNotExistByTitle() {
        // given
        String title = "title";

        // when
        List<Book> actual = bookRepository.findAllByTitleIgnoreCaseContaining(title);

        // then
        assertThat(actual).isEmpty();
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }
}