package com.example.product_service.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection="recommendation")
@Data
public class Recommendation {

    @Id
    String id = UUID.randomUUID().toString();
    String productId;
    String userId;
    Boolean isPrivate;

}
