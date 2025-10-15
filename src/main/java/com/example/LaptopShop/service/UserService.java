/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.service;

import com.example.LaptopShop.domain.Role;
import com.example.LaptopShop.domain.User;
import com.example.LaptopShop.domain.dto.RegisterDTO;
import com.example.LaptopShop.repository.OrderRepository;
import com.example.LaptopShop.repository.ProductRepository;
import com.example.LaptopShop.repository.RoleRepository;
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
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public String handleHello(){
        return "Hello from service";
    }
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }
//    public List<User> getAllUsersByEmail(String email){
//        return this.userRepository.findByEmail(email);
//    }
    public User getUserById(long id){
        return this.userRepository.findById(id).orElse(null);
    }
    public void handleSaveUser(User user){
        this.userRepository.save(user);
    }
    public void deleteUser(long id){
        this.userRepository.deleteById(id);
    }
    public Role getRoleByName(String name){
        return this.roleRepository.findByName(name);
    }
    public User registerDTOToUser(RegisterDTO registerDTO){
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }
    public boolean checkEmailExist(String email){
        return this.userRepository.existsByEmail(email);
    }
    public User getUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public long countUsers() {
        return this.userRepository.count();
    }

    public long countProducts() {
        return this.productRepository.count();
    }

    public long countOrders() {
        return this.orderRepository.count();
    }
}
