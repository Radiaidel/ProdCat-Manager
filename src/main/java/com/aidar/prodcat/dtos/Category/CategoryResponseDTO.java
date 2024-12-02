package com.aidar.prodcat.dtos.Category;

import com.aidar.prodcat.dtos.Product.ProductEmbeddedDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponseDTO {
    private Long id;
    private String name;
    private String description;
    private List<ProductEmbeddedDTO> products;
}
