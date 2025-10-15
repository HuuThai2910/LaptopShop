/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.service;

import com.example.LaptopShop.domain.Order;
import com.example.LaptopShop.domain.User;
import com.example.LaptopShop.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public List<Order> handleGetAllOrders(){
        return orderRepository.findAll();
    }
    public Order handleGetOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }
    public void handleDeleteOrder(Long id){
        Optional<Order> orderOptional = this.orderRepository.findById(id);
        if(orderOptional.isPresent()){
            Order order = orderOptional.get();
            order.getOrderDetails().clear();
            orderRepository.deleteById(id);
        }

    }
    public Order handleSave(Order order){
        return orderRepository.save(order);
    }

    public List<Order> fetchOrderByUser(User currentUser) {
        return this.orderRepository.findByUser(currentUser);
    }
}
