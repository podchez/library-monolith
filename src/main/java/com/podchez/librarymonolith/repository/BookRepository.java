package com.podchez.librarymonolith.repository;

import com.podchez.librarymonolith.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
