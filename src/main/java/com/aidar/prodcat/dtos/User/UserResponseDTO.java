package com.aidar.prodcat.dtos.User;


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
    private List<String> roles;
    private Boolean active;
}
