package com.aidar.prodcat.mappers;

import com.aidar.prodcat.dtos.Category.CategoryRequestDTO;
import com.aidar.prodcat.dtos.Category.CategoryResponseDTO;
import com.aidar.prodcat.models.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface CategoryMapper {

    Category toEntity(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO toResponseDTO(Category category);

}
