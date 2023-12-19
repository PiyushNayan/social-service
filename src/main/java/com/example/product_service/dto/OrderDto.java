package com.example.product_service.dto;


import com.example.product_service.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private String userId;
    private List<String> productIds;
    private double totalPrice;
}
