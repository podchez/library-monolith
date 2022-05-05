package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();

    RoleDto findById(Long id);

    RoleDto save(RoleDto roleDto);

    RoleDto update(Long id, RoleDto roleDto);

    void deleteById(Long id);
}
