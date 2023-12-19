package com.example.product_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "product-merchant")
public class ProductMerchant {

    @Id
    private String productMerchantId;
    private String merchantId;
    private String productId;
    private int rating;
    private int stocks;
    private int price;
//    private int unitPrice;
}
