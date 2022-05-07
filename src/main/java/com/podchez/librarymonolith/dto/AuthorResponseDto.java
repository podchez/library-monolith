package com.podchez.librarymonolith.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {

    private Long id;

    private String fullName;

    private Set<BookResponseDto> books;
}
