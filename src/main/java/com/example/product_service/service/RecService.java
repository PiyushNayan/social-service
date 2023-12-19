package com.example.product_service.service;

import com.example.product_service.dto.RecommendationDto;
import com.example.product_service.entity.Recommendation;

import java.util.List;

public interface RecService {

    Boolean addEntry(RecommendationDto recommendationDto);
    List<String> getFriendsWhoBoughtProduct(String productId, String currentUserId);

}
