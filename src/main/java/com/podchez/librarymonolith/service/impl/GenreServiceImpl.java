package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.dto.GenreRequestDto;
import com.podchez.librarymonolith.dto.GenreResponseDto;
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
    public List<GenreResponseDto> findAll() {
        return genreRepository.findAll().stream()
                .map(genreMapper::toRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenreResponseDto findById(Integer id) {
        return genreMapper.toRespDto(genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id)));
    }

    @Override
    public GenreResponseDto findByName(String name) {
        return genreMapper.toRespDto(genreRepository.findByName(name)
                .orElseThrow(() -> new GenreNotFoundException(name)));
    }

    @Override
    public GenreResponseDto save(GenreRequestDto genreReqDto) {
        String genreName = genreReqDto.getName();
        if (genreRepository.findByName(genreName).isPresent()) {
            throw new GenreAlreadyExistsException(genreName);
        }

        Genre savedGenre = genreRepository.save(genreMapper.toEntity(genreReqDto));
        return genreMapper.toRespDto(savedGenre);
    }

    @Override
    public GenreResponseDto update(Integer id, GenreRequestDto genreReqDto) {
        if (!genreRepository.existsById(id)) {
            throw new GenreNotFoundException(id);
        }

        String genreName = genreReqDto.getName();
        Optional<Genre> genreWithSameName = genreRepository.findByName(genreName);
        if (genreWithSameName.isPresent() &&
                !genreWithSameName.get().getId().equals(id)) {
            throw new GenreAlreadyExistsException(genreName);
        }

        Genre genre = genreMapper.toEntity(genreReqDto);
        genre.setId(id); // to avoid saving

        Genre updatedGenre = genreRepository.save(genre);
        return genreMapper.toRespDto(updatedGenre);
    }

    @Override
    public void deleteById(Integer id) {
        if (!genreRepository.existsById(id)) {
            throw new GenreNotFoundException(id);
        }
        genreRepository.deleteById(id);
    }
}
