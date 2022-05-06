package com.podchez.librarymonolith.repository;

import com.podchez.librarymonolith.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByTitleIgnoreCaseContaining(String title);
}
