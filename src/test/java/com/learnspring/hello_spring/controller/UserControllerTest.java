package com.learnspring.hello_spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.learnspring.hello_spring.dto.request.UserCreationRequest;
import com.learnspring.hello_spring.dto.response.UserResponse;
import com.learnspring.hello_spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private UserCreationRequest request;
    private UserResponse response;
    private LocalDate dob;

    @BeforeEach
    void initData(){
        dob = LocalDate.of(1997, 9, 2);

        request = UserCreationRequest.builder()
                .username("jayden14")
                .firstName("Doctor")
                .lastName("Strange")
                .password("123456789")
                .dateOfBirth(dob)
                .build();

        response = UserResponse.builder()
                .id("8b149b2d-2ddb-474e-9342-b20000cd49c7")
                .username("jayden14")
                .firstName("Doctor")
                .lastName("Strange")
                .dateOfBirth(dob)
                .build();
    }

    @Test
    // Test for creating a user with valid request data
    void createUser_validRequest_success() throws Exception {
        log.info("User creation test executed");

        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String content = objectMapper.writeValueAsString(request);

        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(response);

        // WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value("8b149b2d-2ddb-474e-9342-b20000cd49c7"));

    }

    @Test
    void createUser_invalidUsername_failure() throws Exception{
        log.info("User creation with invalid username test executed");

        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        request.setUsername("ja"); // Invalid username (less than 3 characters)

        String content = objectMapper.writeValueAsString(request);

        // WHEN, THEN
        mockMvc.perform(MockMvcRequestBuilders
                .post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1002)) // USERNAME_INVALID code
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Username must be at least 3 characters long"));
    }
}
