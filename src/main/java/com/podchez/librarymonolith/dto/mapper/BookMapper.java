package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.BookDto;
import com.podchez.librarymonolith.entity.Author;
import com.podchez.librarymonolith.entity.Book;
import com.podchez.librarymonolith.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<Book, BookDto> {
    @Override
    public BookDto toDto(Book entity) {
        return BookDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .pages(entity.getPages())
                .priceInCents(entity.getPriceInCents())
                .isAvailable(entity.getIsAvailable())
                .genreName(entity.getGenre().getName())
                .authorFullName(entity.getAuthor().getFullName())
                .build();
    }

    @Override
    public Book toEntity(BookDto dto) {
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .pages(dto.getPages())
                .priceInCents(dto.getPriceInCents())
                .isAvailable(dto.getIsAvailable())
                .genre(new Genre(null, dto.getGenreName(), null))
                .author(new Author(null, dto.getAuthorFullName(), null))
                .build();
    }
}
