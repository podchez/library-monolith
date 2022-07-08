package com.podchez.librarymonolith.service;

import com.podchez.librarymonolith.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Integer id);

    Role findByName(String name);

    void save(Role role);

    void update(Integer id, Role role);

    void delete(Integer id);
}
