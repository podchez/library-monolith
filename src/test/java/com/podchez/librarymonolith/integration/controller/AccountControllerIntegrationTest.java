package com.podchez.librarymonolith.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.podchez.librarymonolith.dto.JwtTokenDto;
import com.podchez.librarymonolith.integration.annotation.IntegrationTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IntegrationTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountControllerIntegrationTest {

    private final MockMvc mockMvc;

    @Test
    void accessShouldBeDenied_whenRequestWithoutJwtToken() throws Exception {
        mockMvc.perform(get("/api/v1/accounts")) // without JWT token
                .andExpect(status().isForbidden());

        String jwtTokenAsJson = mockMvc.perform(post("/api/v1/accounts/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin\",\"password\":\"admin\"}"))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        JwtTokenDto jwtToken = new ObjectMapper().readValue(jwtTokenAsJson, JwtTokenDto.class);

        mockMvc.perform(get("/api/v1/accounts")  // with JWT token
                        .header("Authorization", "Bearer " + jwtToken.getJwtToken()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
