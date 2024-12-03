package com.aidar.prodcat.services.impl;

import com.aidar.prodcat.dtos.User.UserRequestDTO;
import com.aidar.prodcat.dtos.User.UserResponseDTO;
import com.aidar.prodcat.exceptions.UserAlreadyExistsException;
import com.aidar.prodcat.mappers.UserMapper;
import com.aidar.prodcat.models.Role;
import com.aidar.prodcat.models.User;
import com.aidar.prodcat.repository.UserRepository;
import com.aidar.prodcat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDTO registerNewUser(UserRequestDTO userDtoRequest) {
        if (userRepository.existsByEmail(userDtoRequest.getEmail())) {
            throw new UserAlreadyExistsException("Login already exists");
        }

        User user = userMapper.toEntity(userDtoRequest);
        user.setPassword(passwordEncoder.encode(userDtoRequest.getPassword()));
        user.setActive(true);


        if (user.getRoles() == null) {
            user.setRoles(Collections.singleton(Role.builder().name("User").build()));
        }

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponseDTO loginUser(UserRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new  BadCredentialsException("No user found with this email"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        if (!user.getActive()== true) {
            throw new BadCredentialsException("User account is not active");
        }

        return userMapper.toResponse(user);
    }
}