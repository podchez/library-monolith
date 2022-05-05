package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.GenreDto;
import com.podchez.librarymonolith.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GenreMapper implements Mapper<Genre, GenreDto> {

    private final BookMapper bookMapper;

    @Autowired
    public GenreMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public GenreDto toDto(Genre entity) {
        return GenreDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .books(entity.getBooks().stream()
                        .map(bookMapper::toDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Genre toEntity(GenreDto dto) {
        return Genre.builder()
                .id(dto.getId())
                .name(dto.getName())
                .books(dto.getBooks().stream()
                        .map(bookMapper::toEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
