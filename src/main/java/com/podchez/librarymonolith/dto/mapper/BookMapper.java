package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.BookRequestDto;
import com.podchez.librarymonolith.dto.BookResponseDto;
import com.podchez.librarymonolith.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookRequestDto, Book, BookResponseDto> {
    @Override
    public Book toEntity(BookRequestDto reqDto) {
        return Book.builder()
                .title(reqDto.getTitle())
                .pages(reqDto.getPages())
                .priceInCents(reqDto.getPriceInCents())
                .build();
    }

    @Override
    public BookResponseDto toRespDto(Book entity) {
        return BookResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .pages(entity.getPages())
                .priceInCents(entity.getPriceInCents())
                .isAvailable(entity.getIsAvailable())
                .authorFullName(entity.getAuthor().getFullName())
                .build();
    }
}
