/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.repository;

import com.example.LaptopShop.domain.Order;
import com.example.LaptopShop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
