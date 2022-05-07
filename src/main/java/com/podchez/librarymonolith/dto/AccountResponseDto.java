package com.podchez.librarymonolith.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean isEnabled;

    private Set<String> roleNames;
}
