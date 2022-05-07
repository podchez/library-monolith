package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.BookRequestDto;
import com.podchez.librarymonolith.dto.BookResponseDto;

import java.util.List;

public interface BookService {

    List<BookResponseDto> findAll();

    List<BookResponseDto> findAllByTitle(String title);

    BookResponseDto findById(Long id);

    BookResponseDto save(BookRequestDto bookReqDto);

    BookResponseDto update(Long id, BookRequestDto bookReqDto);

    void deleteById(Long id);
}
