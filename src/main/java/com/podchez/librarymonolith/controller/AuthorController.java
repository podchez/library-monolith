package com.podchez.librarymonolith.controller;

import com.podchez.librarymonolith.dto.AuthorRequestDto;
import com.podchez.librarymonolith.dto.AuthorResponseDto;
import com.podchez.librarymonolith.dto.mapper.AuthorMapper;
import com.podchez.librarymonolith.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper mapper;

    @Autowired
    public AuthorController(AuthorService authorService, AuthorMapper mapper) {
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200
    public List<AuthorResponseDto> findAll() {
        return authorService.findAll().stream()
                .map(mapper::toRespDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public AuthorResponseDto findById(@PathVariable Long id) {
        return mapper.toRespDto(authorService.findById(id));
    }

    @GetMapping("/fullName/{fullName}")
    @ResponseStatus(HttpStatus.OK) // 200
    public AuthorResponseDto findByFullName(@PathVariable String fullName) {
        return mapper.toRespDto(authorService.findByFullName(fullName));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void save(@RequestBody AuthorRequestDto authorReqDto) {
        authorService.save(mapper.toEntity(authorReqDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void update(@PathVariable Long id, @RequestBody AuthorRequestDto authorReqDto) {
        authorService.update(id, mapper.toEntity(authorReqDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
