package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.AccountRequestDto;
import com.podchez.librarymonolith.dto.AccountResponseDto;
import com.podchez.librarymonolith.entity.Account;
import com.podchez.librarymonolith.entity.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountMapper implements Mapper<AccountRequestDto, Account, AccountResponseDto> {
    @Override
    public Account toEntity(AccountRequestDto reqDto) {
        return Account.builder()
                .firstName(reqDto.getFirstName())
                .lastName(reqDto.getLastName())
                .email(reqDto.getEmail())
                .password(reqDto.getPassword())
                .roles(reqDto.getRoleNames().stream()
                        .map(roleName -> new Role(null, roleName, null))
                        .collect(Collectors.toSet()))
                // the roleNames->roles mapping with all checks is in the AccountService
                .build();
    }

    @Override
    public AccountResponseDto toRespDto(Account entity) {
        return AccountResponseDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .isEnabled(entity.getIsEnabled())
                .roleNames(entity.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()))
                .build();
    }
}
