package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.RoleRequestDto;
import com.podchez.librarymonolith.dto.RoleResponseDto;

import java.util.List;

public interface RoleService {

    List<RoleResponseDto> findAll();

    RoleResponseDto findById(Integer id);

    RoleResponseDto findByName(String name);

    RoleResponseDto save(RoleRequestDto roleReqDto);

    RoleResponseDto update(Integer id, RoleRequestDto roleReqDto);

    void deleteById(Integer id);
}
