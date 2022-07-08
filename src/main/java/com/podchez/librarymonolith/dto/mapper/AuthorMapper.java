package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.AuthorRequestDto;
import com.podchez.librarymonolith.dto.AuthorResponseDto;
import com.podchez.librarymonolith.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Mapper<AuthorRequestDto, Author, AuthorResponseDto> {
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
                .build();
    }
}
