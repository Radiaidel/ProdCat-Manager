package com.aidar.prodcat.models;


import com.aidar.prodcat.models.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Login cannot be blank")
    @Column(unique = true)
    private String login;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private Boolean active;

    private Role role;

}
