package com.example.product_service.repository;

import com.example.product_service.entity.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String> {
    Boolean existsByProductCategoryName(String ProductCategoryName);
    String findProductCategoryIdByProductCategoryName(String ProductCategoryName);
}
