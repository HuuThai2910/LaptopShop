/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.controller.admin;

import com.example.LaptopShop.domain.Order;
import com.example.LaptopShop.domain.Product;
import com.example.LaptopShop.repository.OrderRepository;
import com.example.LaptopShop.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
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
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/admin/order")
    public String getOrderPage(Model model){
        List<Order> orders = orderService.handleGetAllOrders();
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }
    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(Model model, @PathVariable long id){
        Order order = orderService.handleGetOrderById(id);
        model.addAttribute("currentOrder", order);
        return "admin/order/update";
    }
    @PostMapping("/admin/order/update")
    public String updateOrder(@ModelAttribute("currentOrder") Order order){
        Order currentOrder = orderService.handleGetOrderById(order.getId());
        if(currentOrder != null){
            currentOrder.setStatus(order.getStatus());
            orderService.handleSave(currentOrder);
        }
        return "redirect:/admin/order";
    }
    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable long id){
        Order order = orderService.handleGetOrderById(id);
        model.addAttribute("order", order);
        return "admin/order/detail";
    }
    @GetMapping("admin/order/delete/{id}")
    public String getDeleteOrderPage(@PathVariable long id, Model model){
        Order order = orderService.handleGetOrderById(id);
        model.addAttribute("order", order);
        return "admin/order/delete";
    }
    @PostMapping("/admin/order/delete")
    public String deleteOrder(@ModelAttribute("order") Order order){
        this.orderService.handleDeleteOrder(order.getId());
        return "redirect:/admin/order";
    }
}
