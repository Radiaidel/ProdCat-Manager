package com.aidar.prodcat.dtos.User;


import com.aidar.prodcat.models.enums.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;
    private String email;
    private Role role;
    private Boolean active;
}
