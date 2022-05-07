package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.dto.BookRequestDto;
import com.podchez.librarymonolith.dto.BookResponseDto;
import com.podchez.librarymonolith.dto.mapper.BookMapper;
import com.podchez.librarymonolith.entity.Author;
import com.podchez.librarymonolith.entity.Book;
import com.podchez.librarymonolith.entity.Genre;
import com.podchez.librarymonolith.exception.AuthorNotFoundException;
import com.podchez.librarymonolith.exception.BookNotFoundException;
import com.podchez.librarymonolith.exception.GenreNotFoundException;
import com.podchez.librarymonolith.repository.AuthorRepository;
import com.podchez.librarymonolith.repository.BookRepository;
import com.podchez.librarymonolith.repository.GenreRepository;
import com.podchez.librarymonolith.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper,
                           GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookResponseDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDto> findAllByTitle(String title) {
        return bookRepository.findAllByTitleIgnoreCaseContaining(title).stream()
                .map(bookMapper::toRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto findById(Long id) {
        return bookMapper.toRespDto(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Override
    public BookResponseDto save(BookRequestDto bookReqDto) {
        Book book = bookMapper.toEntity(bookReqDto);
        book.setGenre(getGenreFromBookReqDto(bookReqDto));
        book.setAuthor(getAuthorFromBookReqDto(bookReqDto));

        Book savedBook = bookRepository.save(book);
        return bookMapper.toRespDto(savedBook);
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto bookReqDto) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }

        Book book = bookMapper.toEntity(bookReqDto);
        book.setGenre(getGenreFromBookReqDto(bookReqDto));
        book.setAuthor(getAuthorFromBookReqDto(bookReqDto));
        book.setId(id); // to avoid saving

        Book updatedBook = bookRepository.save(book);
        return bookMapper.toRespDto(updatedBook);
    }

    @Override
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }

    // Helper method
    private Genre getGenreFromBookReqDto(BookRequestDto bookReqDto) {
        String genreName = bookReqDto.getGenreName();
        return genreRepository.findByName(genreName)
                .orElseThrow(() -> new GenreNotFoundException(genreName));
    }

    // Helper method
    private Author getAuthorFromBookReqDto(BookRequestDto bookReqDto) {
        String authorFullName = bookReqDto.getAuthorFullName();
        return authorRepository.findByFullName(authorFullName)
                .orElseThrow(() -> new AuthorNotFoundException(authorFullName));
    }
}
