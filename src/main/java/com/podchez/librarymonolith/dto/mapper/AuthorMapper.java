package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.AuthorDto;
import com.podchez.librarymonolith.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorMapper implements Mapper<Author, AuthorDto> {

    private final BookMapper bookMapper;

    @Autowired
    public AuthorMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public AuthorDto toDto(Author entity) {
        return AuthorDto.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .books(entity.getBooks().stream()
                        .map(bookMapper::toDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Author toEntity(AuthorDto dto) {
        return Author.builder()
                .id(dto.getId())
                .fullName(dto.getFullName())
                .books(dto.getBooks().stream()
                        .map(bookMapper::toEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
