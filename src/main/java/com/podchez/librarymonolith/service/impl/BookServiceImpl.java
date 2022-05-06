package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.dto.BookDto;
import com.podchez.librarymonolith.dto.mapper.BookMapper;
import com.podchez.librarymonolith.entity.Book;
import com.podchez.librarymonolith.exception.BookNotFoundException;
import com.podchez.librarymonolith.repository.BookRepository;
import com.podchez.librarymonolith.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findAllByTitle(String title) {
        return bookRepository.findAllByTitleIgnoreCaseContaining(title).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public BookDto save(BookDto bookDto) {
        bookDto.setId(null); // to avoid updating
        Book savedBook = bookRepository.save(bookMapper.toEntity(bookDto));
        return bookMapper.toDto(savedBook);
    }

    @Override
    public BookDto update(Long id, BookDto bookDto) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }

        bookDto.setId(id); // bookDto should have correct id
        Book updatedBook = bookRepository.save(bookMapper.toEntity(bookDto));
        return bookMapper.toDto(updatedBook);
    }

    @Override
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
}
