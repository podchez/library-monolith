package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.exception.AuthorNotFoundException;
import com.podchez.librarymonolith.model.Author;
import com.podchez.librarymonolith.model.Book;
import com.podchez.librarymonolith.exception.BookNotFoundException;
import com.podchez.librarymonolith.repository.AuthorRepository;
import com.podchez.librarymonolith.repository.BookRepository;
import com.podchez.librarymonolith.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final Validator validator;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, Validator validator) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.validator = validator;
    }

    @Override
    public List<Book> findAll() {
        log.info("IN findAll");
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllByTitle(String title) {
        log.info("IN findAllByTitle");
        return bookRepository.findAllByTitleIgnoreCaseContaining(title);
    }

    @Override
    public Book findById(Long id) {
        log.info("IN findById - id: {}", id);
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("There is no book with ID = " + id + " in the database."));
    }

    @Override
    @Transactional
    public void save(Book book, String authorFullName) {
        validate(book);

        Author author = authorRepository.findByFullName(authorFullName)
                .orElseThrow(() -> new AuthorNotFoundException(("There is no author with full name '" + authorFullName + "' in the DB")));

        book.setAuthor(author);
        book.setIsAvailable(true);

        author.getBooks().add(book);

        bookRepository.save(book);
        log.info("IN save - book with title: {} saved", book.getTitle());
    }

    @Override
    @Transactional
    public void update(Long id, Book book, String authorFullName) {
        validate(book);

        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("There is no book with ID = " + id + " in the database.");
        }

        Author author = authorRepository.findByFullName(authorFullName)
                .orElseThrow(() -> new AuthorNotFoundException(("There is no author with full name '" + authorFullName + "' in the DB")));

        book.setAuthor(author);
        book.setId(id);

        author.getBooks().remove(book);
        author.getBooks().add(book);

        bookRepository.save(book);
        log.info("IN update - book with id: {} updated", id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("There is no book with ID = " + id + " in the database.");
        }
        bookRepository.deleteById(id);
        log.info("IN delete - book with id: {} deleted", id);
    }

    private void validate(Book book) {
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Book> violation : violations) {
                sb.append(violation.getMessage() + "; ");
            }
            throw new ConstraintViolationException(sb.toString(), violations);
        }
    }
}
