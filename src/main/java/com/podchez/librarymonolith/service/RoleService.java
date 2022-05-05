package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();

    RoleDto findById(Integer id);

    RoleDto save(RoleDto roleDto);

    RoleDto update(Integer id, RoleDto roleDto);

    void deleteById(Integer id);
}
