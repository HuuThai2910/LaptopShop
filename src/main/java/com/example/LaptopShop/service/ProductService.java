/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.service;

import com.example.LaptopShop.domain.*;
import com.example.LaptopShop.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

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


    @Transactional
    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverAddress, String receiverPhone) {
//        Create order detail
//        Step 1: get cart by user
        Cart cart = this.cartRepository.findByUser(user);
        if(cart != null){
            List<CartDetail> cartDetails = cart.getCartDetails();
            if(cartDetails != null){
                //        Create order
                Order order = new Order();
                order.setUser(user);
                order.setStatus("PENDING");
                order.setOrderDate(LocalDate.now());
                order.setReceiverName(receiverName);
                order.setReceiverPhone(receiverPhone);
                order.setReceiverAddress(receiverAddress);
                double sum  = 0;
                for(CartDetail cd : cartDetails){
                    sum += cd.getPrice();
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);
                for(CartDetail cd : cartDetails){
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setQuantity(cd.getQuantity());
                    orderDetail.setPrice(cd.getPrice());
                    this.orderDetailRepository.save(orderDetail);
                }
            }
//            Step 2: delete cart_detail and cart

            cart.getCartDetails().clear();
            cartRepository.delete(cart);
//            Step 3: update session
            session.setAttribute("sum", 0);
        }
    }
}
