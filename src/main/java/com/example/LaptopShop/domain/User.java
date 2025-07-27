/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.domain;

import com.example.LaptopShop.service.validator.StrongPassword;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String fullName;
//    @StrongPassword(message = "Pass phai co 8 ky tu")
    private String password;
    private String address;
    private String phone;
    private String avatar;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
