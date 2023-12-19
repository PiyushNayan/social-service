package com.example.product_service.repository;


public interface ProductMerchantCustomRepository {
     void updateStocksByProductIdAndMerchantId(String productId, String merchantId,int count);
}
