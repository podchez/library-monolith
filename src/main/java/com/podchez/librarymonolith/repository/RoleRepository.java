package com.podchez.librarymonolith.repository;

import com.podchez.librarymonolith.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
