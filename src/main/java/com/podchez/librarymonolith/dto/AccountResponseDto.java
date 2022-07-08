package com.podchez.librarymonolith.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

    private Long id;

    private String username;

    private String email;

    private Boolean isEnabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<String> roleNames;
}
