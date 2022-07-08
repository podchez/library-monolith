package com.podchez.librarymonolith.dto.mapper;

import com.podchez.librarymonolith.dto.RoleRequestDto;
import com.podchez.librarymonolith.dto.RoleResponseDto;
import com.podchez.librarymonolith.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements Mapper<RoleRequestDto, Role, RoleResponseDto> {
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
                .build();
    }
}
