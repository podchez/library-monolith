package com.podchez.librarymonolith.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDto {

    private String title;

    private Integer pages;

    private Long priceInCents;

    private String genreName;

    private String authorFullName;
}
