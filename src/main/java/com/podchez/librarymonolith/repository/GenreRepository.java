package com.podchez.librarymonolith.repository;

import com.podchez.librarymonolith.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByName(String name);
}
