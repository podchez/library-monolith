package com.podchez.librarymonolith.dto;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {

    private String username;

    private String email;

    private String password;
}
