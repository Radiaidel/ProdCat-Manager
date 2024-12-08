package com.aidar.prodcat.controllers;

import com.aidar.prodcat.dtos.User.UserRequestDTO;
import com.aidar.prodcat.models.User;
import com.aidar.prodcat.models.enums.Role;
import com.aidar.prodcat.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @Mock
    private UserService userService;

    @MockBean
    private AuthenticationManager authenticationManager;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void testRegisterUserAlreadyExists() throws Exception {
        UserRequestDTO userDTO = new UserRequestDTO();
        userDTO.setUsername("existingUser");
        userDTO.setPassword("password123");
        userDTO.setRole(Role.ROLE_USER.toString());

        when(userService.existsByUsername("existingUser")).thenReturn(true);

        mockMvc.perform(post("/api/auth/register")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username is already taken."));
    }

    @Test
    public void testRegisterNewUser() throws Exception {
        UserRequestDTO userDTO = new UserRequestDTO();
        userDTO.setUsername("new");
        userDTO.setPassword("password123");
        userDTO.setRole(Role.ROLE_ADMIN.toString());

        when(userService.existsByUsername("neww")).thenReturn(false);

        mockMvc.perform(post("/api/auth/register")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("User registered successfully."));
    }


    @Test
    public void testLoginSuccess() throws Exception {
        UserRequestDTO loginRequest = new UserRequestDTO();
        loginRequest.setUsername("username");
        loginRequest.setPassword("password");

        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        when(userService.existsByUsername("username")).thenReturn(true);

        when(authenticationManager.authenticate(any())).thenReturn(null);

        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("User logged in successfully."));
    }

    @Test
    public void testLoginFailure() throws Exception {
        UserRequestDTO loginRequest = new UserRequestDTO();
        loginRequest.setUsername("invalidUser");
        loginRequest.setPassword("invalidPassword");

        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Invalid credentials"));

        mockMvc.perform(post("/api/auth/login")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }


}

