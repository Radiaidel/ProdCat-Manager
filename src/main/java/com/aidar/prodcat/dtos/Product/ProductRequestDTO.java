package com.aidar.prodcat.dtos.Product;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDTO {
    @NotBlank(message = "Designation cannot be blank")
    private String designation;

    @NotNull(message = "Price cannot be null")
    private Double price;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;

}
