package com.example.product_service.repository;

import com.example.product_service.entity.Recommendation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecCustomRepository {

    public List<Recommendation> getRecommendationByProductId(String productId);
}
