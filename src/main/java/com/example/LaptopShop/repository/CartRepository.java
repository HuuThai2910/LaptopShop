/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.repository;

import com.example.LaptopShop.domain.Cart;
import com.example.LaptopShop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
