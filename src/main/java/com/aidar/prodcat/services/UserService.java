package com.aidar.prodcat.services;

import com.aidar.prodcat.dtos.User.UserRequestDTO;
import com.aidar.prodcat.dtos.User.UserResponseDTO;
import com.aidar.prodcat.models.User;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUserRole(Long id, String role);

    void saveUser(User user);
    boolean existsByUsername(String username);

}
