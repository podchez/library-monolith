package com.podchez.librarymonolith.repository;

import com.podchez.librarymonolith.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
