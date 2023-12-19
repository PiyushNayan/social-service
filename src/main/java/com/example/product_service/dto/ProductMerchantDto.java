package com.example.product_service.dto;

import lombok.Data;

@Data
public class ProductMerchantDto {

    private String merchantId;
    private String productId;
    private int rating;
    private int stocks;
}
