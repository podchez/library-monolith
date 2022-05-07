package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.GenreRequestDto;
import com.podchez.librarymonolith.dto.GenreResponseDto;

import java.util.List;

public interface GenreService {

    List<GenreResponseDto> findAll();

    GenreResponseDto findById(Integer id);

    GenreResponseDto findByName(String name);

    GenreResponseDto save(GenreRequestDto genreReqDto);

    GenreResponseDto update(Integer id, GenreRequestDto genreReqDto);

    void deleteById(Integer id);
}
