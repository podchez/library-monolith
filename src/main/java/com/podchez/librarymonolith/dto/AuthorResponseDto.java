package com.podchez.librarymonolith.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {

    private Long id;

    private String fullName;
}
