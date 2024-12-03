package com.aidar.prodcat.mappers;


import com.aidar.prodcat.dtos.Product.ProductRequestDTO;
import com.aidar.prodcat.dtos.Product.ProductResponseDTO;
import com.aidar.prodcat.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
//    @Mapping(source = "categoryId", target = "category.id")
//    Product toEntity(ProductRequestDTO dto);
//
//    @Mapping(source = "category", target = "category")
//    ProductResponseDTO toDTO(Product entity);
}
