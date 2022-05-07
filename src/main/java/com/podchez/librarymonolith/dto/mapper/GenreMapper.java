package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.GenreRequestDto;
import com.podchez.librarymonolith.dto.GenreResponseDto;
import com.podchez.librarymonolith.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GenreMapper implements Mapper<GenreRequestDto, Genre, GenreResponseDto> {

    private final BookMapper bookMapper;

    @Autowired
    public GenreMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public Genre toEntity(GenreRequestDto reqDto) {
        return Genre.builder()
                .name(reqDto.getName())
                .build();
    }

    @Override
    public GenreResponseDto toRespDto(Genre entity) {
        return GenreResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .books(entity.getBooks().stream()
                        .map(bookMapper::toRespDto)
                        .collect(Collectors.toSet()))
                .build();
    }
}
