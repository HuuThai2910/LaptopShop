/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty(message = "Ten san pham khong duoc trong")
    private String name;

    @NotNull
    @DecimalMin(value = "0", inclusive = false, message = "Price phai lon hon 0")
    private double price;

    private String image;

    @NotNull
    @NotEmpty(message = "Detail desc khong duoc de trong")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String detailDesc;

    @NotNull
    @NotEmpty(message = "Short desc khong duoc de trong")
    private String shortDesc;
    @NotNull
    @Min(value = 1, message = "So luong can lon hon hoac bang 1")
    private long quantity;
    private long sold;
    private String factory;
    private String target;

}
