package com.example.product_service.repository;

import com.example.product_service.entity.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecRepository extends MongoRepository<Recommendation, String> {

//    List<Recommendation> findAllByProductId(String productId);

    List<String> findUserIdsByProductIdAndIsPrivateFalse(String productId);


}

