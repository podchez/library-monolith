package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.AccountRequestDto;
import com.podchez.librarymonolith.dto.AccountResponseDto;
import com.podchez.librarymonolith.model.Account;
import com.podchez.librarymonolith.model.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountMapper implements Mapper<AccountRequestDto, Account, AccountResponseDto> {
    @Override
    public Account toEntity(AccountRequestDto reqDto) {
        return Account.builder()
                .username(reqDto.getUsername())
                .email(reqDto.getEmail())
                .password(reqDto.getPassword())
                .build();
    }

    @Override
    public AccountResponseDto toRespDto(Account entity) {
        return AccountResponseDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .isEnabled(entity.getIsEnabled())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .roleNames(entity.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
