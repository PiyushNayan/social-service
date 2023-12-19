package com.example.product_service.dto;

import lombok.Data;

@Data
public class MerchantDto {
    private String merchantId;
    private int stocks;
    private int rating;
}
