package com.example.product_service.repository;

import com.example.product_service.entity.Order;
import com.example.product_service.entity.Product;
import com.example.product_service.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
