package com.aidar.prodcat.dtos.User;

import com.aidar.prodcat.models.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private Boolean active;
    private Role role;

}
