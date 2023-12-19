package com.example.product_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class ProductFeignDto {
    private double price;
    private String productId;
    private String productName;
    private String merchantId;
    private double merchantRating;
    private int productsSold;
    private int productsAvailable;
    private String imageUrl;
}
