package com.aidar.prodcat.dtos.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String description;
}
