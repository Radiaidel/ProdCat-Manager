package com.aidar.prodcat.dtos.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String description;
}
