package com.aidar.prodcat.dtos.Product;

import com.aidar.prodcat.dtos.Category.CategoryEmbeddedDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponseDTO {
    private Long id;
    private String designation;
    private Double price;
    private Integer quantity;
    private CategoryEmbeddedDTO category;
}
