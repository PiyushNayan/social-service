package com.example.product_service.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection="order")
public class Order {
    @Id
    private String orderId=UUID.randomUUID().toString();
    private String userId;
    private Product product;
}
