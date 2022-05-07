package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.dto.RoleRequestDto;
import com.podchez.librarymonolith.dto.RoleResponseDto;
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
    public List<RoleResponseDto> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDto findById(Integer id) {
        return roleMapper.toRespDto(roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id)));
    }

    @Override
    public RoleResponseDto findByName(String name) {
        return roleMapper.toRespDto(roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException(name)));
    }

    @Override
    public RoleResponseDto save(RoleRequestDto roleReqDto) {
        String roleName = roleReqDto.getName();
        if (roleRepository.findByName(roleName).isPresent()) {
            throw new RoleAlreadyExistsException(roleName);
        }

        Role savedRole = roleRepository.save(roleMapper.toEntity(roleReqDto));
        return roleMapper.toRespDto(savedRole);
    }

    @Override
    public RoleResponseDto update(Integer id, RoleRequestDto roleReqDto) {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException(id);
        }

        String roleName = roleReqDto.getName();
        Optional<Role> roleWithSameName = roleRepository.findByName(roleName);
        if (roleWithSameName.isPresent() &&
                !roleWithSameName.get().getId().equals(id)) {
            throw new RoleAlreadyExistsException(roleName);
        }

        Role role = roleMapper.toEntity(roleReqDto);
        role.setId(id); // to avoid saving

        Role updatedRole = roleRepository.save(role);
        return roleMapper.toRespDto(updatedRole);
    }

    @Override
    public void deleteById(Integer id) {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException(id);
        }
        roleRepository.deleteById(id);
    }
}
