package com.aidar.prodcat.services.impl;

import com.aidar.prodcat.dtos.Product.ProductRequestDTO;
import com.aidar.prodcat.dtos.Product.ProductResponseDTO;
import com.aidar.prodcat.mappers.ProductMapper;
import com.aidar.prodcat.models.Product;
import com.aidar.prodcat.repository.ProductRepository;
import com.aidar.prodcat.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Page<ProductResponseDTO> listProducts(int page, int size, String sortBy) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy == null ? "id" : sortBy)))
                .map(productMapper::toResponseDTO);
    }


    @Override
    public Page<ProductResponseDTO> searchProductsByCategory(Long categoryId, int page, int size, String sortBy) {
        return productRepository.findByCategoryId(categoryId, PageRequest.of(page, size, Sort.by(sortBy == null ? "id" : sortBy)))
                .map(productMapper::toResponseDTO);
    }

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        product = productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product = productMapper.toEntity(productRequestDTO);
        product = productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
