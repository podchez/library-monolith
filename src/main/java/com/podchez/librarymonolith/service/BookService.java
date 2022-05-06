package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.BookDto;

import java.util.List;

public interface BookService {

    List<BookDto> findAll();

    List<BookDto> findAllByTitle(String name);

    BookDto findById(Long id);

    BookDto save(BookDto bookDto);

    BookDto update(Long id, BookDto bookDto);

    void deleteById(Long id);
}
