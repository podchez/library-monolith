package com.podchez.librarymonolith.controller;

import com.podchez.librarymonolith.dto.RoleRequestDto;
import com.podchez.librarymonolith.dto.RoleResponseDto;
import com.podchez.librarymonolith.dto.mapper.RoleMapper;
import com.podchez.librarymonolith.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper mapper;

    @Autowired
    public RoleController(RoleService roleService, RoleMapper mapper) {
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200
    public List<RoleResponseDto> findAll() {
        return roleService.findAll().stream()
                .map(mapper::toRespDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public RoleResponseDto findById(@PathVariable Integer id) {
        return mapper.toRespDto(roleService.findById(id));
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK) // 200
    public RoleResponseDto findByName(@PathVariable String name) {
        return mapper.toRespDto(roleService.findByName(name));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void save(@RequestBody RoleRequestDto roleReqDto) {
        roleService.save(mapper.toEntity(roleReqDto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void update(@PathVariable Integer id, @RequestBody RoleRequestDto roleReqDto) {
        roleService.update(id, mapper.toEntity(roleReqDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void delete(@PathVariable Integer id) {
        roleService.delete(id);
    }
}
