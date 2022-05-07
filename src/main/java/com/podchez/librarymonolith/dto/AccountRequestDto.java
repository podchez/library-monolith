package com.podchez.librarymonolith.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Set<String> roleNames;
}
