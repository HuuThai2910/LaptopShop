/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.service;

import com.example.LaptopShop.domain.Cart;
import com.example.LaptopShop.domain.CartDetail;
import com.example.LaptopShop.domain.Product;
import com.example.LaptopShop.domain.User;
import com.example.LaptopShop.repository.CartDetailRepository;
import com.example.LaptopShop.repository.CartRepository;
import com.example.LaptopShop.repository.ProductRepository;
import com.example.LaptopShop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
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
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;
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
    public void handleAddProduct(String email, Long productId, HttpSession session){
        User user = this.userService.getUserByEmail(email);
        if(user != null){
            Cart cart = this.cartRepository.findByUser(user);
            if(cart == null){
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);
                cart = this.cartRepository.save(otherCart);
            }
            Optional<Product> productOptional = this.productRepository.findById(productId);
            if(productOptional.isPresent()){
                Product realProduct = productOptional.get();
//                Check san pham da duoc them vao gio hang truoc day chua
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
                if(oldDetail == null){
                    CartDetail cd = new CartDetail();
                    cd.setCart(cart);
                    cd.setProduct(realProduct);
                    cd.setPrice(realProduct.getPrice());
                    cd.setQuantity(1);
                    this.cartDetailRepository.save(cd);
//                   update cart
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    System.out.println(cart.getSum());
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                }else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(oldDetail);
                }

            }

        }
    }
    public Cart getCartByUser(User user){
        return this.cartRepository.findByUser(user);
    }
    public void removeCartDetailFromCart(Long id, HttpSession session){
        Optional<CartDetail> optionalCartDetail = this.cartDetailRepository.findCartDetailById(id);
        if(optionalCartDetail.isPresent()){
            CartDetail cartDetail = optionalCartDetail.get();
            Cart cart = cartDetail.getCart();
            this.cartDetailRepository.deleteById(cartDetail.getId());
            int s = cart.getSum() - 1;
            cart.setSum(s);
            session.setAttribute("sum", s);
            this.cartRepository.save(cart);
        }
    }
    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails){
        for(CartDetail cartDetail : cartDetails){
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity (cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }

    }

}
