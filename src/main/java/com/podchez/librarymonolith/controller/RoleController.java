package com.podchez.librarymonolith.controller;

import com.podchez.librarymonolith.dto.RoleRequestDto;
import com.podchez.librarymonolith.dto.RoleResponseDto;
import com.podchez.librarymonolith.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200
    public List<RoleResponseDto> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public RoleResponseDto findById(@PathVariable Integer id) {
        return roleService.findById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK) // 200
    public RoleResponseDto findByName(@PathVariable String name) {
        return roleService.findByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public RoleResponseDto save(@RequestBody RoleRequestDto roleReqDto) {
        return roleService.save(roleReqDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public RoleResponseDto update(@PathVariable Integer id, @RequestBody RoleRequestDto roleReqDto) {
        return roleService.update(id, roleReqDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteById(@PathVariable Integer id) {
        roleService.deleteById(id);
    }
}
