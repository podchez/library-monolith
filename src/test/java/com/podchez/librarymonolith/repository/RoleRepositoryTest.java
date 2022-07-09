package com.podchez.librarymonolith.repository;

import com.podchez.librarymonolith.model.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void shouldFindRole_whenRoleExistsByName() {
        // given
        String name = "ROLE_DUMMY";
        Role role = new Role(null, name, null);
        roleRepository.save(role);

        // when
        Optional<Role> actual = roleRepository.findByName(name);

        // then
        assertThat(actual).isPresent();
        actual.ifPresent(actualRole ->
                assertThat(actualRole.getName()).isEqualTo(name));
    }

    @Test
    void shouldNotFindRole_whenRoleDoesNotExistByName() {
        // given
        String name = "ROLE_DUMMY";

        // when
        Optional<Role> actual = roleRepository.findByName(name);

        // then
        assertThat(actual).isEmpty();
    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }
}