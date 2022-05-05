package com.podchez.librarymonolith.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Set<String> roleNames;
}
