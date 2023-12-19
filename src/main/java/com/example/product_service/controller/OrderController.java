package com.example.product_service.controller;

import com.example.product_service.dto.OrderDto;
import com.example.product_service.entity.Order;
import com.example.product_service.entity.Product;
import com.example.product_service.entity.Recommendation;
import com.example.product_service.exceptionHandler.ApiResponse;
import com.example.product_service.repository.RecRepository;
import com.example.product_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")

@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RecRepository recRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addOrder(@RequestBody Order order)  {
        try {

            Boolean result = orderService.addOrder(order);
            Recommendation recommendation = new Recommendation();
            recommendation.setIsPrivate(false);
            recommendation.setProductId(order.getProduct().getProductId());

            recommendation.setUserId(order.getUserId());

            recRepository.save(recommendation);


            if (result) {
                return new ResponseEntity<>("Order added successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to add order", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/orders")
    public ApiResponse<List<Order>> getAllOrders() {
        ApiResponse<List<Order>> apiResponse;
        try {
            List<Order> orders = orderService.getAllOrders();
            apiResponse = new ApiResponse<>(orders);

        } catch (Exception e) {
            apiResponse = new ApiResponse<>("404", "No products found");
        }


        return apiResponse;
    }
}
