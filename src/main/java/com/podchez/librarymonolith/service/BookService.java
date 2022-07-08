package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    List<Book> findAllByTitle(String title);

    Book findById(Long id);

    void save(Book book, String authorFullName);

    void update(Long id, Book book, String authorFullName);

    void delete(Long id);
}
