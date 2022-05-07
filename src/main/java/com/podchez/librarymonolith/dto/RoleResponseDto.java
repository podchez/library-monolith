package com.podchez.librarymonolith.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto {

    private Integer id;

    private String name;

    private Set<AccountResponseDto> accounts;
}
