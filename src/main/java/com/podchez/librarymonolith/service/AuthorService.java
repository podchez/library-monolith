package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(Long id);

    Author findByFullName(String fullName);

    void save(Author author);

    void update(Long id, Author author);

    void delete(Long id);
}
