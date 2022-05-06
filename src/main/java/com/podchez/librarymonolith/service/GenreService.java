package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.GenreDto;

import java.util.List;

public interface GenreService {

    List<GenreDto> findAll();

    GenreDto findById(Integer id);

    GenreDto save(GenreDto genreDto);

    GenreDto update(Integer id, GenreDto genreDto);

    void deleteById(Integer id);
}
