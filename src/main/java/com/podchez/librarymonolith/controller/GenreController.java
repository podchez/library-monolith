package com.podchez.librarymonolith.controller;

import com.podchez.librarymonolith.dto.GenreRequestDto;
import com.podchez.librarymonolith.dto.GenreResponseDto;
import com.podchez.librarymonolith.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200
    public List<GenreResponseDto> findAll() {
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public GenreResponseDto findById(@PathVariable Integer id) {
        return genreService.findById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK) // 200
    public GenreResponseDto findByName(@PathVariable String name) {
        return genreService.findByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public GenreResponseDto save(@RequestBody GenreRequestDto genreReqDto) {
        return genreService.save(genreReqDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public GenreResponseDto update(@PathVariable Integer id, @RequestBody GenreRequestDto genreReqDto) {
        return genreService.update(id, genreReqDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteById(@PathVariable Integer id) {
        genreService.deleteById(id);
    }
}
