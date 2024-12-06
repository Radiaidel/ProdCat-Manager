package com.aidar.prodcat.services.impl;

import com.aidar.prodcat.dtos.Category.CategoryRequestDTO;
import com.aidar.prodcat.dtos.Category.CategoryResponseDTO;
import com.aidar.prodcat.dtos.Product.ProductResponseDTO;
import com.aidar.prodcat.mappers.CategoryMapper;
import com.aidar.prodcat.mappers.ProductMapper;
import com.aidar.prodcat.models.Category;
import com.aidar.prodcat.models.Product;
import com.aidar.prodcat.repository.CategoryRepository;
import com.aidar.prodcat.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListCategories_Success() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("id"));
        Category category = Category.builder()
                .id(1L)
                .name("Category 1")
                .description("Description 1")
                .build();
        Page<Category> categoryPage = new PageImpl<>(Arrays.asList(category));

        when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);
        when(categoryMapper.toResponseDTO(any())).thenReturn(CategoryResponseDTO.builder()
                .id(1L)
                .name("Category 1")
                .description("Description 1")
                .build());

        Page<CategoryResponseDTO> result = categoryService.listCategories(0, 10, "id");

        assertEquals(1, result.getTotalElements());
        assertEquals("Category 1", result.getContent().get(0).getName());
    }

    @Test
    void testListCategories_EmptyResult() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("id"));
        when(categoryRepository.findAll(pageable)).thenReturn(Page.empty());

        Page<CategoryResponseDTO> result = categoryService.listCategories(0, 10, "id");

        assertTrue(result.isEmpty());
    }

    @Test
    void testSearchCategories_Success() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("id"));
        Category category = Category.builder()
                .id(1L)
                .name("Category 1")
                .description("Description 1")
                .build();
        Page<Category> categoryPage = new PageImpl<>(Arrays.asList(category));

        when(categoryRepository.findByNameContainingIgnoreCase("Category", pageable)).thenReturn(categoryPage);
        when(categoryMapper.toResponseDTO(any())).thenReturn(CategoryResponseDTO.builder()
                .id(1L)
                .name("Category 1")
                .description("Description 1")
                .build());

        Page<CategoryResponseDTO> result = categoryService.searchCategories("Category", 0, 10, "id");

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testSearchCategories_EmptyResult() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("id"));
        when(categoryRepository.findByNameContainingIgnoreCase("NonExisting", pageable)).thenReturn(Page.empty());

        Page<CategoryResponseDTO> result = categoryService.searchCategories("NonExisting", 0, 10, "id");

        assertTrue(result.isEmpty());
    }

    @Test
    void testListProductsInCategory_Success() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("id"));

        ProductResponseDTO productResponseDTO = ProductResponseDTO.builder()
                .id(1L)
                .designation("Product 1")
                .quantity(12)
                .price(100.0)
                .build();

        Product product = Product.builder()
                .id(1L)
                .designation("Product 1")
                .quantity(12)
                .price(100.0)
                .build();

        Page<Product> productPage = new PageImpl<>(Arrays.asList(product));

        when(productRepository.findByCategoryId(1L, pageable)).thenReturn(productPage);
        when(productMapper.toResponseDTO(any(Product.class))).thenReturn(productResponseDTO);

        Page<ProductResponseDTO> result = categoryService.listProductsInCategory(1L, 0, 10, "id");

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testListProductsInCategory_EmptyResult() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("id"));
        when(productRepository.findByCategoryId(1L, pageable)).thenReturn(Page.empty());

        Page<ProductResponseDTO> result = categoryService.listProductsInCategory(1L, 0, 10, "id");

        assertTrue(result.isEmpty());
    }

    @Test
    void testAddCategory_Success() {
        CategoryRequestDTO requestDTO =  CategoryRequestDTO.builder().name("Category 1").description("Description 1").build();
        Category category = Category.builder()
                .id(1L)
                .name("Category 1")
                .description("Description 1")
                .build();

        when(categoryMapper.toEntity(requestDTO)).thenReturn(category);
        when(categoryRepository.save(any())).thenReturn(category);
        when(categoryMapper.toResponseDTO(any())).thenReturn(CategoryResponseDTO.builder()
                .id(1L)
                .name("Category 1")
                .description("Description 1")
                .build());

        CategoryResponseDTO result = categoryService.addCategory(requestDTO);

        assertEquals("Category 1", result.getName());
    }

    @Test
    void testUpdateCategory_Success() throws Exception {
        CategoryRequestDTO requestDTO =  CategoryRequestDTO.builder().name("Updated Name").description("Updated Description").build();
        Category category = Category.builder()
                .id(1L)
                .name("Old Name")
                .description("Old Description")
                .build();

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any())).thenReturn(category);
        when(categoryMapper.toResponseDTO(any())).thenReturn(CategoryResponseDTO.builder()
                .id(1L)
                .name("Updated Name")
                .description("Updated Description")
                .build());

        CategoryResponseDTO result = categoryService.updateCategory(1L, requestDTO);

        assertEquals("Updated Name", result.getName());
    }

    @Test
    void testUpdateCategory_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> categoryService.updateCategory(1L,  CategoryRequestDTO.builder().name("Category 1").description("Description 1").build()));

        assertEquals("Category not found", exception.getMessage());
    }

    @Test
    void testDeleteCategory_Success() {
        doNothing().when(categoryRepository).deleteById(1L);

        assertDoesNotThrow(() -> categoryService.deleteCategory(1L));
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
