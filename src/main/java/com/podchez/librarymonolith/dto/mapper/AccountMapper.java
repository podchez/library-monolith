package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.AccountDto;
import com.podchez.librarymonolith.entity.Account;
import com.podchez.librarymonolith.entity.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountMapper implements Mapper<Account, AccountDto> {
    @Override
    public AccountDto toDto(Account entity) {
        return AccountDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .roleNames(entity.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Account toEntity(AccountDto dto) {
        return Account.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .roles(dto.getRoleNames().stream()
                        .map(roleName -> new Role(null, roleName, null))
                        .collect(Collectors.toSet()))
                .build();
    }
}
