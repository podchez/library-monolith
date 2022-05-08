package com.podchez.librarymonolith.controller;

import com.podchez.librarymonolith.dto.AuthorRequestDto;
import com.podchez.librarymonolith.dto.AuthorResponseDto;
import com.podchez.librarymonolith.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200
    public List<AuthorResponseDto> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public AuthorResponseDto findById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @GetMapping("/fullName/{fullName}")
    @ResponseStatus(HttpStatus.OK) // 200
    public AuthorResponseDto findByFullName(@PathVariable String fullName) {
        return authorService.findByFullName(fullName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public AuthorResponseDto save(@RequestBody AuthorRequestDto authorReqDto) {
        return authorService.save(authorReqDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public AuthorResponseDto update(@PathVariable Long id, @RequestBody AuthorRequestDto authorReqDto) {
        return authorService.update(id, authorReqDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}
