/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.controller.client;

import com.example.LaptopShop.domain.Cart;
import com.example.LaptopShop.domain.CartDetail;
import com.example.LaptopShop.domain.Product;
import com.example.LaptopShop.domain.User;
import com.example.LaptopShop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

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
    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        long productId = id;
        String email = (String) session.getAttribute("email");
        this.productService.handleAddProduct(email, productId, session);
        return "redirect:/";
    }
    @PostMapping("/remove-cartDetail-from-cart/{id}")
    public String removeProductFromCart(@PathVariable long id, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        long cartDetailId = id;
        this.productService.removeCartDetailFromCart(cartDetailId, session);
        return "redirect:/cart";
    }
    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request){
        User currentUser = new User();
        HttpSession session = request.getSession(false);
        long id = (long)session.getAttribute("id");
        currentUser.setId(id);
        Cart cart = this.productService.getCartByUser(currentUser);
        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        double totalPrice = 0;
        for(CartDetail cd : cartDetails){
            totalPrice += cd.getPrice() * cd.getQuantity();
        }
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cart", cart);
        return "client/cart/show";
    }
    @GetMapping("/checkout")
    public String getCheckoutPage(){
        return "client/checkout/show";
    }
    @PostMapping("/confirm-checkout")
    public String getCheckoutPage(@ModelAttribute("cart") Cart cart){
        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        this.productService.handleUpdateCartBeforeCheckout(cartDetails);
        return "redirect:/checkout";
    }
}
