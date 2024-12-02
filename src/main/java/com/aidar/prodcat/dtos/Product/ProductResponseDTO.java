package com.aidar.prodcat.dtos.Product;

import com.aidar.prodcat.dtos.Category.CategoryEmbeddedDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String designation;
    private Double price;
    private Integer quantity;
    private CategoryEmbeddedDTO category;
}
