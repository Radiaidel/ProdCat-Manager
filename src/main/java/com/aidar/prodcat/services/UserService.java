package com.aidar.prodcat.services;

import com.aidar.prodcat.dtos.User.UserRequestDTO;
import com.aidar.prodcat.dtos.User.UserResponseDTO;

public interface UserService {
    UserResponseDTO registerNewUser(UserRequestDTO userDtoRequest);
}
