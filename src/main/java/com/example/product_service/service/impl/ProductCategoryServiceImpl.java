package com.example.product_service.service.impl;

import com.example.product_service.entity.ProductCategory;
import com.example.product_service.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ProductCategoryServiceImpl {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public boolean productCategoryExists(String productCategoryName) {
        return productCategoryRepository.existsByProductCategoryName(productCategoryName);
    }

    public ProductCategory createProductCategory (String productCategoryName) {
        if(!productCategoryExists(productCategoryName)) {
            ProductCategory newProductCategory = new ProductCategory();
            newProductCategory.setProductCategoryId(UUID.randomUUID().toString());
            newProductCategory.setProductCategoryName(productCategoryName);
            return productCategoryRepository.save(newProductCategory);
        }
        return null;
    }

}
