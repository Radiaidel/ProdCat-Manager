package com.aidar.prodcat.services.impl;

import com.aidar.prodcat.dtos.User.UserRequestDTO;
import com.aidar.prodcat.dtos.User.UserResponseDTO;
import com.aidar.prodcat.exceptions.UserAlreadyExistsException;
import com.aidar.prodcat.mappers.UserMapper;
import com.aidar.prodcat.models.User;
import com.aidar.prodcat.models.enums.Role;
import com.aidar.prodcat.repository.UserRepository;
import com.aidar.prodcat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUserRole(Long id, String role) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(Role.ROLE_ADMIN);
        User updatedUser = userRepository.save(user);

        return userMapper.toResponse(updatedUser);
    }

}