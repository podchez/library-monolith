package com.podchez.librarymonolith.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreResponseDto {

    private Integer id;

    private String name;

    private Set<BookResponseDto> books;
}
