package com.aidar.prodcat.controllers;

import com.aidar.prodcat.dtos.Category.CategoryRequestDTO;
import com.aidar.prodcat.dtos.Category.CategoryResponseDTO;
import com.aidar.prodcat.dtos.Product.ProductResponseDTO;
import com.aidar.prodcat.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/user/categories", "/admin/categories"})
    public Page<CategoryResponseDTO> listCategories(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String sortBy) {
        return categoryService.listCategories(page, size, sortBy);
    }

    @GetMapping({"/user/categories/search", "/admin/categories/search"})
    public Page<CategoryResponseDTO> searchCategories(
            @RequestParam String name,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String sortBy) {
        return categoryService.searchCategories(name, page, size, sortBy);
    }

    @GetMapping({"/user/categories/{categoryId}/products", "/admin/categories/{categoryId}/products"})
    public Page<ProductResponseDTO> listProductsInCategory(
            @PathVariable Long categoryId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String sortBy) {
        return categoryService.listProductsInCategory(categoryId, page, size, sortBy);
    }

    @PostMapping("/admin/categories")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(categoryRequestDTO));
    }

    @PutMapping("/admin/categories/{categoryId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryRequestDTO categoryRequestDTO) throws Exception {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryRequestDTO));
    }

    @DeleteMapping("/admin/categories/{categoryId}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
