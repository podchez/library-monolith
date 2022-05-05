package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> findAll();

    GenreDto findById(Long id);

    GenreDto save(GenreDto genreDto);

    GenreDto update(Long id, GenreDto genreDto);

    void deleteById(Long id);
}
