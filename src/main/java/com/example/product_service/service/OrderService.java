package com.example.product_service.service;

import com.example.product_service.dto.OrderDto;
import com.example.product_service.entity.Order;
import com.example.product_service.entity.Product;

import java.util.List;

public interface OrderService {
    Boolean addOrder(Order order);
    List<Order> getAllOrders();
}
