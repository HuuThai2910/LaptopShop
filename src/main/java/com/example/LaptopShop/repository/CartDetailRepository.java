/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.repository;

import com.example.LaptopShop.domain.Cart;
import com.example.LaptopShop.domain.CartDetail;
import com.example.LaptopShop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    boolean existsByCartAndProduct(Cart cart, Product product);

    CartDetail findByCartAndProduct(Cart cart, Product product);

    void removeCartDetailById(long id);

    Optional<CartDetail> findCartDetailById(long id);
}
