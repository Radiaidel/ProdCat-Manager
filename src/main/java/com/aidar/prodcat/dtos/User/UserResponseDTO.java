package com.aidar.prodcat.dtos.User;

import com.aidar.prodcat.models.enums.Role;

public class UserResponseDTO {
    private Long id;
    private String email;
    private String password;
    private Boolean active;
    private Role role;
}
