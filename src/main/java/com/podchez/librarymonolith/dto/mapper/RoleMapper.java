package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.RoleRequestDto;
import com.podchez.librarymonolith.dto.RoleResponseDto;
import com.podchez.librarymonolith.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleMapper implements Mapper<RoleRequestDto, Role, RoleResponseDto> {

    private final AccountMapper accountMapper;

    @Autowired
    public RoleMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Role toEntity(RoleRequestDto reqDto) {
        return Role.builder()
                .name(reqDto.getName())
                .build();
    }

    @Override
    public RoleResponseDto toRespDto(Role entity) {
        return RoleResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .accounts(entity.getAccounts().stream()
                        .map(accountMapper::toRespDto)
                        .collect(Collectors.toSet()))
                .build();
    }
}
