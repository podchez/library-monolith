package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.BookRequestDto;
import com.podchez.librarymonolith.dto.BookResponseDto;
import com.podchez.librarymonolith.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<BookRequestDto, Book, BookResponseDto> {
    @Override
    public Book toEntity(BookRequestDto reqDto) {
        return Book.builder()
                .title(reqDto.getTitle())
                .pages(reqDto.getPages())
                .priceInCents(reqDto.getPriceInCents())
                // the genreName->genre mapping with all checks is in the BookService
                // the authorFullName->author mapping with all checks is in the BookService
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
                .genreName(entity.getGenre().getName())
                .authorFullName(entity.getAuthor().getFullName())
                .build();
    }
}
