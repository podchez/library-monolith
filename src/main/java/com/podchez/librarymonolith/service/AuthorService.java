package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> findAll();

    AuthorDto findById(Long id);

    AuthorDto save(AuthorDto authorDto);

    AuthorDto update(Long id, AuthorDto authorDto);

    void deleteById(Long id);
}
