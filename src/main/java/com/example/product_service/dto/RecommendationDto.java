package com.example.product_service.dto;

import lombok.Data;

@Data
public class RecommendationDto {
    String id;
    String productId;
    String userId;
    Boolean isPrivate;
}
