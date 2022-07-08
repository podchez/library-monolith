package com.podchez.librarymonolith.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {

    private Long id;

    private String title;

    private Integer pages;

    private Long priceInCents;

    private Boolean isAvailable;

    private String authorFullName;
}
