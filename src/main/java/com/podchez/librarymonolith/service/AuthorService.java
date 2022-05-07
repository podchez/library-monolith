package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.AuthorRequestDto;
import com.podchez.librarymonolith.dto.AuthorResponseDto;

import java.util.List;

public interface AuthorService {

    List<AuthorResponseDto> findAll();

    AuthorResponseDto findById(Long id);

    AuthorResponseDto findByFullName(String fullName);

    AuthorResponseDto save(AuthorRequestDto authorReqDto);

    AuthorResponseDto update(Long id, AuthorRequestDto authorReqDto);

    void deleteById(Long id);
}
