package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.RoleDto;
import com.podchez.librarymonolith.entity.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleMapper implements Mapper<Role, RoleDto> {

    private final AccountMapper accountMapper;

    public RoleMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public RoleDto toDto(Role entity) {
        return RoleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .accounts(entity.getAccounts().stream()
                        .map(accountMapper::toDto)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Role toEntity(RoleDto dto) {
        return Role.builder()
                .id(dto.getId())
                .name(dto.getName())
                .accounts(dto.getAccounts().stream()
                        .map(accountMapper::toEntity)
                        .collect(Collectors.toSet()))
                .build();
    }
}
