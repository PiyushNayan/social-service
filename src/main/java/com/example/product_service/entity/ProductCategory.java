package com.example.product_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ProductCategory {

    @Id
    private String productCategoryId;

    private String productCategoryName;

}
