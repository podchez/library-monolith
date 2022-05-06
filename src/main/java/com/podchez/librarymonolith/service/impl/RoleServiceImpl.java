package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.dto.RoleDto;
import com.podchez.librarymonolith.dto.mapper.RoleMapper;
import com.podchez.librarymonolith.entity.Role;
import com.podchez.librarymonolith.exception.RoleAlreadyExistsException;
import com.podchez.librarymonolith.exception.RoleNotFoundException;
import com.podchez.librarymonolith.repository.RoleRepository;
import com.podchez.librarymonolith.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto findById(Integer id) {
        return roleMapper.toDto(roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id)));
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        String roleName = roleDto.getName();
        if (roleRepository.findByName(roleName).isPresent()) {
            throw new RoleAlreadyExistsException(roleName);
        }

        roleDto.setId(null); // to avoid updating
        Role savedRole = roleRepository.save(roleMapper.toEntity(roleDto));
        return roleMapper.toDto(savedRole);
    }

    @Override
    public RoleDto update(Integer id, RoleDto roleDto) {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException(id);
        }

        String roleName = roleDto.getName();
        Optional<Role> roleWithSameName = roleRepository.findByName(roleName);
        if (roleWithSameName.isPresent() &&
                !roleWithSameName.get().getId().equals(id)) {
            throw new RoleAlreadyExistsException(roleName);
        }

        roleDto.setId(id); // roleDto should have correct id
        Role updatedRole = roleRepository.save(roleMapper.toEntity(roleDto));
        return roleMapper.toDto(updatedRole);
    }

    @Override
    public void deleteById(Integer id) {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException(id);
        }
        roleRepository.deleteById(id);
    }
}
