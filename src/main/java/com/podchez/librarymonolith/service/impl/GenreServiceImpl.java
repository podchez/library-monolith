package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.dto.GenreDto;
import com.podchez.librarymonolith.dto.mapper.GenreMapper;
import com.podchez.librarymonolith.entity.Genre;
import com.podchez.librarymonolith.exception.GenreAlreadyExistsException;
import com.podchez.librarymonolith.exception.GenreNotFoundException;
import com.podchez.librarymonolith.repository.GenreRepository;
import com.podchez.librarymonolith.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreDto> findAll() {
        return genreRepository.findAll().stream()
                .map(genreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDto findById(Integer id) {
        return genreMapper.toDto(genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id)));
    }

    @Override
    public GenreDto save(GenreDto genreDto) {
        String genreName = genreDto.getName();
        if (genreRepository.findByName(genreName).isPresent()) {
            throw new GenreAlreadyExistsException(genreName);
        }

        genreDto.setId(null); // to avoid updating
        Genre savedGenre = genreRepository.save(genreMapper.toEntity(genreDto));
        return genreMapper.toDto(savedGenre);
    }

    @Override
    public GenreDto update(Integer id, GenreDto genreDto) {
        if (!genreRepository.existsById(id)) {
            throw new GenreNotFoundException(id);
        }

        String genreName = genreDto.getName();
        Optional<Genre> genreWithSameName = genreRepository.findByName(genreName);
        if (genreWithSameName.isPresent() &&
                !genreWithSameName.get().getId().equals(id)) {
            throw new GenreAlreadyExistsException(genreName);
        }

        genreDto.setId(id); // genreDto should have correct id
        Genre updatedGenre = genreRepository.save(genreMapper.toEntity(genreDto));
        return genreMapper.toDto(updatedGenre);
    }

    @Override
    public void deleteById(Integer id) {
        if (!genreRepository.existsById(id)) {
            throw new GenreNotFoundException(id);
        }
        genreRepository.deleteById(id);
    }
}
