/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.controller.client;

import com.example.LaptopShop.domain.Product;
import com.example.LaptopShop.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Controller
@AllArgsConstructor
public class ItemController {
    private final ProductService productService;
    @GetMapping("/product/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "client/product/detail";
    }
}
