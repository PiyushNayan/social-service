package com.example.product_service.repository.impl;

import com.example.product_service.entity.ProductMerchant;
import com.example.product_service.entity.Recommendation;
import com.example.product_service.repository.ProductMerchantCustomRepository;
import com.example.product_service.repository.RecCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecCustomRepositoryImpl implements RecCustomRepository {


    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Recommendation> getRecommendationByProductId(String productId){
        Query query = new Query();
        query.addCriteria(Criteria.where("productId").is(productId));

        List<Recommendation> recommendations =  mongoTemplate.findAll(Recommendation.class);
        return recommendations;
    }

}


