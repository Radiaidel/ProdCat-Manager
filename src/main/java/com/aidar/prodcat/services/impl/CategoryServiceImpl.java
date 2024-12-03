package com.aidar.prodcat.services.impl;

import com.aidar.prodcat.dtos.Category.CategoryRequestDTO;
import com.aidar.prodcat.dtos.Category.CategoryResponseDTO;
import com.aidar.prodcat.dtos.Product.ProductResponseDTO;
import com.aidar.prodcat.mappers.CategoryMapper;
import com.aidar.prodcat.mappers.ProductMapper;
import com.aidar.prodcat.models.Category;
import com.aidar.prodcat.repository.CategoryRepository;
import com.aidar.prodcat.repository.ProductRepository;
import com.aidar.prodcat.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;
    @Override
    public Page<CategoryResponseDTO> listCategories(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy != null ? sortBy : "id"));
        return categoryRepository.findAll(pageable).map(categoryMapper::toResponseDTO);
    }

    @Override
    public Page<CategoryResponseDTO> searchCategories(String name, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy != null ? sortBy : "id"));
        return categoryRepository.findByNameContainingIgnoreCase(name, pageable).map(categoryMapper::toResponseDTO);
    }

    @Override
    public Page<ProductResponseDTO> listProductsInCategory(Long categoryId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy != null ? sortBy : "id"));
        return productRepository.findByCategoryId(categoryId, pageable).map(productMapper::toResponseDTO);
    }

    @Override
    public CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category =  categoryMapper.toEntity(categoryRequestDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new Exception("Category not found"));
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
