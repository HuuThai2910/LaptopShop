/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.controller.admin;

import com.example.LaptopShop.domain.Product;
import com.example.LaptopShop.service.ProductService;
import com.example.LaptopShop.service.UploadService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Controller
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;
    @GetMapping("/admin/product")
    public String getProductPage(Model model){
        List<Product> products = productService.getAllProducts();

        model.addAttribute("products", products);
        return "admin/product/show";
    }
    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model){
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }
    @PostMapping("/admin/product/create")
    public String createUserPage(@ModelAttribute("newProduct") @Valid Product product,
                                 BindingResult newProductBindingResult,
                                 @RequestParam("thaiFile")MultipartFile file){
        if(newProductBindingResult.hasErrors()){
            return "admin/product/create";
        }
        String image = this.uploadService.handleSaveUploadFile(file, "product");
        product.setImage(image);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id){
        Product product = productService.getProductById(id);
        model.addAttribute("currentProduct", product);
        return "admin/product/update";
    }
    @PostMapping("/admin/product/update")
    public String postUpdateProduct(@ModelAttribute("currentProduct") @Valid Product product,
                                    BindingResult currentProductBindingResult,
                                    @RequestParam("thaiFile") MultipartFile file){
        if(currentProductBindingResult.hasErrors()){
            return "admin/product/update";
        }
        Product currentProduct = this.productService.getProductById(product.getId());
        System.out.println(product);
        System.out.println(currentProduct);
        if(currentProduct != null){
            if(!file.isEmpty()){
                System.out.println(file);
                String img = this.uploadService.handleSaveUploadFile(file, "product");
                currentProduct.setImage(img);
            }

            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setFactory(product.getFactory());
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setQuantity(product.getQuantity());
            currentProduct.setTarget(product.getTarget());
            this.productService.handleSaveProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }
    @GetMapping("admin/product/delete/{id}")
    public String getDeleteProductPage(@PathVariable long id, Model model){
        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("currentProduct", currentProduct);
        return "admin/product/delete";
    }
    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(Model model, @ModelAttribute("currentProduct") Product product){
        this.productService.deleteProduct(product.getId());
        return "redirect:/admin/product";
    }
}
