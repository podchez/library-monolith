package com.podchez.librarymonolith.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto {

    private Integer id;

    private String name;
}
