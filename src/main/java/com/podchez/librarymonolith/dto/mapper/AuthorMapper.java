package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.AuthorRequestDto;
import com.podchez.librarymonolith.dto.AuthorResponseDto;
import com.podchez.librarymonolith.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorMapper implements Mapper<AuthorRequestDto, Author, AuthorResponseDto> {

    private final BookMapper bookMapper;

    @Autowired
    public AuthorMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public Author toEntity(AuthorRequestDto reqDto) {
        return Author.builder()
                .fullName(reqDto.getFullName())
                .build();
    }

    @Override
    public AuthorResponseDto toRespDto(Author entity) {
        return AuthorResponseDto.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .books(entity.getBooks().stream()
                        .map(bookMapper::toRespDto)
                        .collect(Collectors.toSet()))
                .build();
    }
}
