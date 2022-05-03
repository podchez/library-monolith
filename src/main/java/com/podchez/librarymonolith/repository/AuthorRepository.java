package com.podchez.librarymonolith.repository;

import com.podchez.librarymonolith.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
