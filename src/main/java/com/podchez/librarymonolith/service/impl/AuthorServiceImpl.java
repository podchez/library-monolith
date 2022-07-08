package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.exception.AuthorAlreadyExistsException;
import com.podchez.librarymonolith.exception.AuthorNotFoundException;
import com.podchez.librarymonolith.model.Author;
import com.podchez.librarymonolith.repository.AuthorRepository;
import com.podchez.librarymonolith.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final Validator validator;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, Validator validator) {
        this.authorRepository = authorRepository;
        this.validator = validator;
    }

    @Override
    public List<Author> findAll() {
        log.info("IN findAll");
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        log.info("IN findById - id: {}", id);
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("There is no author with ID " + id + " in the DB"));
    }

    @Override
    public Author findByFullName(String fullName) {
        log.info("IN findByFullName - fullName: {}", fullName);
        return authorRepository.findByFullName(fullName)
                .orElseThrow(() -> new AuthorNotFoundException("There is no author with full name '" + fullName + "' in the DB"));
    }

    @Override
    @Transactional
    public void save(Author author) {
        validate(author);

        String fullName = author.getFullName();
        if (authorRepository.findByFullName(fullName).isPresent()) {
            throw new AuthorAlreadyExistsException("Author with full name '" + fullName + "' already exists");
        }

        authorRepository.save(author);
        log.info("IN save - author with fullName: {} saved", fullName);
    }

    @Override
    @Transactional
    public void update(Long id, Author author) {
        validate(author);

        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException("There is no author with ID " + id + " in the DB");
        }

        String fullName = author.getFullName();
        authorRepository.findByFullName(fullName).ifPresent((authorWithSameFullName -> {
            if (!authorWithSameFullName.getId().equals(id)) {
                throw new AuthorAlreadyExistsException("Author with full name '" + fullName + "' already exists");
            }
        }));

        author.setId(id);
        authorRepository.save(author);
        log.info("IN update - author with id: {} updated", id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException("There is no author with ID " + id + " in the DB");
        }
        authorRepository.deleteById(id);
        log.info("IN delete - author with id: {} deleted", id);
    }

    private void validate(Author author) {
        Set<ConstraintViolation<Author>> violations = validator.validate(author);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Author> violation : violations) {
                sb.append(violation.getMessage() + "; ");
            }
            throw new ConstraintViolationException(sb.toString(), violations);
        }
    }
}
