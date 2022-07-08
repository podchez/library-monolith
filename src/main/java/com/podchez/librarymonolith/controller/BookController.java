package com.podchez.librarymonolith.controller;

import com.podchez.librarymonolith.dto.BookRequestDto;
import com.podchez.librarymonolith.dto.BookResponseDto;
import com.podchez.librarymonolith.dto.mapper.BookMapper;
import com.podchez.librarymonolith.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper mapper;

    @Autowired
    public BookController(BookService bookService, BookMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200
    public List<BookResponseDto> findAll() {
        return bookService.findAll().stream()
                .map(mapper::toRespDto)
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"title"})
    @ResponseStatus(HttpStatus.OK) // 200
    public List<BookResponseDto> findAllByTitle(@RequestParam String title) {
        return bookService.findAllByTitle(title).stream()
                .map(mapper::toRespDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public BookResponseDto findById(@PathVariable Long id) {
        return mapper.toRespDto(bookService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void save(@RequestBody BookRequestDto bookReqDto) {
        bookService.save(mapper.toEntity(bookReqDto), bookReqDto.getAuthorFullName());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void update(@PathVariable Long id, @RequestBody BookRequestDto bookReqDto) {
        bookService.update(id, mapper.toEntity(bookReqDto), bookReqDto.getAuthorFullName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteById(@PathVariable Long id) {
        bookService.delete(id);
    }
}
