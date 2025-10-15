/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.domain;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 0)
    private int sum;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartDetail> cartDetails;
}
