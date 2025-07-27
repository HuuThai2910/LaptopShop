/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.service;

import com.example.LaptopShop.domain.Product;
import com.example.LaptopShop.repository.ProductRepository;
import com.example.LaptopShop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }
    public Product getProductById(Long id){
        return this.productRepository.findById(id).orElse(null);
    }
    public void handleSaveProduct(Product product){
        this.productRepository.save(product);
    }
    public void deleteProduct(Long id){
        this.productRepository.deleteById(id);
    }

}
