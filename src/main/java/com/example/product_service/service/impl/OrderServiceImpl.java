package com.example.product_service.service.impl;

import com.example.product_service.dto.OrderDto;
import com.example.product_service.entity.Order;
import com.example.product_service.entity.Product;
import com.example.product_service.repository.OrderRepository;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.OrderService;
import com.example.product_service.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Boolean addOrder(Order order){
        try {
            order.setOrderId(UUID.randomUUID().toString());
             orderRepository.save(order);

            return true;
//            if (orderDto != null && orderDto.getProductIds()!= null  && orderDto.getUserId() != null) {
//                Order order=new Order();
//                BeanUtils.copyProperties(orderDto, order);
//                List<Product> products = productService.getProductsByIds(orderDto.getProductIds());
//
//
//                order.setProducts(products);
//                if (order.getProducts() == null || order.getUserId() == null) {
//                    throw new IllegalArgumentException("Order details cannot be null");
//                }
//                String orderId = UUID.randomUUID().toString();
//                order.setOrderId(orderId);
//                orderRepository.save(order);
//                return true;
//            } else {
//                throw new IllegalArgumentException("OrderDto cannot be null");
//            }
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }


}
