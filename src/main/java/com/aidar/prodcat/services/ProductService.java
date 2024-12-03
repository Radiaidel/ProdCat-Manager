package com.aidar.prodcat.services;

import com.aidar.prodcat.dtos.Product.ProductRequestDTO;
import com.aidar.prodcat.dtos.Product.ProductResponseDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<ProductResponseDTO> listProducts(int page, int size, String sortBy);
    Page<ProductResponseDTO> searchProductsByCategory(Long categoryId, int page, int size, String sortBy);
    ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO);
    void deleteProduct(Long productId);
}
