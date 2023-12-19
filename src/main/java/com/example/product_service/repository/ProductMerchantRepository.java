package com.example.product_service.repository;

import com.example.product_service.entity.ProductMerchant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMerchantRepository extends MongoRepository<ProductMerchant, String> {

    List<ProductMerchant> findByProductId(String productId);

    ProductMerchant findByProductIdAndMerchantId(String productId, String merchantId);

}
