package com.podchez.librarymonolith.controller;

import com.podchez.librarymonolith.dto.BookRequestDto;
import com.podchez.librarymonolith.dto.BookResponseDto;
import com.podchez.librarymonolith.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200
    public List<BookResponseDto> findAll() {
        return bookService.findAll();
    }

    @GetMapping(params = {"title"})
    @ResponseStatus(HttpStatus.OK) // 200
    public List<BookResponseDto> findAllByTitle(@RequestParam String title) {
        return bookService.findAllByTitle(title);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public BookResponseDto findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public BookResponseDto save(@RequestBody BookRequestDto bookReqDto) {
        return bookService.save(bookReqDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public BookResponseDto update(@PathVariable Long id, @RequestBody BookRequestDto bookReqDto) {
        return bookService.update(id, bookReqDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
