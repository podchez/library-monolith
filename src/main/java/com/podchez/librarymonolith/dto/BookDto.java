package com.podchez.librarymonolith.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;

    private String title;

    private Integer pages;

    private Long priceInCents;

    private Boolean isAvailable;

    private GenreDto genre;

    private AuthorDto author;
}
