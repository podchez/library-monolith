package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.model.Role;
import com.podchez.librarymonolith.exception.RoleAlreadyExistsException;
import com.podchez.librarymonolith.exception.RoleNotFoundException;
import com.podchez.librarymonolith.repository.RoleRepository;
import com.podchez.librarymonolith.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final Validator validator;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, Validator validator) {
        this.roleRepository = roleRepository;
        this.validator = validator;
    }

    @Override
    public List<Role> findAll() {
        log.info("IN findAll");
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Integer id) {
        log.info("IN findByName - id: {}", id);
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("There is no role with ID = " + id + " in the database."));
    }

    @Override
    public Role findByName(String name) {
        log.info("IN findByName - name: {}", name);
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("There is no role with name = '" + name + "' in the database."));
    }

    @Override
    @Transactional
    public void save(Role role) {
        validate(role);

        String name = role.getName();
        if (roleRepository.findByName(name).isPresent()) {
            throw new RoleAlreadyExistsException("Role with name '" + name + "' already exists.");
        }

        roleRepository.save(role);
        log.info("IN save - role with name: {} saved", name);
    }

    @Override
    @Transactional
    public void update(Integer id, Role role) {
        validate(role);

        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException("There is no role with ID = " + id + " in the database.");
        }

        String name = role.getName();
        roleRepository.findByName(name).ifPresent(roleWithSameName -> {
            if (!role.getId().equals(id)) {
                throw new RoleAlreadyExistsException("Role with name '" + name + "' already exists.");
            }
        });

        role.setId(id);

        roleRepository.save(role);
        log.info("IN update - role with id: {} updated", id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException("There is no role with ID = " + id + " in the database.");
        }

        roleRepository.deleteById(id);
        log.info("IN delete - role with id: {} deleted", id);
    }

    private void validate(Role role) {
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Role> violation : violations) {
                sb.append(violation.getMessage() + "; ");
            }
            throw new ConstraintViolationException(sb.toString(), violations);
        }
    }
}
