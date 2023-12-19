package com.example.product_service.service;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Boolean addProduct(ProductDto productDto);
    Boolean updateProductById(String productId, ProductDto productDto);
    Optional<Product> getProductById(String productId);
    void deleteProductById(String productId);
    List<Product> getAllProducts();
    List<Product> getProductsByIds(List<String> productIds);
}
