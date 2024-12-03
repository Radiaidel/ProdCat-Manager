package com.aidar.prodcat.dtos.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductEmbeddedDTO {
    private Long id;
    private String designation;
    private Double price;
    private Integer quantity;
}
