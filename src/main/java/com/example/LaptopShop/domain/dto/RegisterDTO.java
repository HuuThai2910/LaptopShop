/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.domain.dto;

import com.example.LaptopShop.service.validator.RegisterChecked;
import com.example.LaptopShop.service.validator.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Getter
@Setter
@RegisterChecked
public class RegisterDTO {
    @Size(min = 3, message = "FirstName phai co toi thieu 3 ky tu")
    private String firstName;

    @Size(min = 3, message = "LastName phai co toi thieu 3 ky tu")
    private String lastName;
    @Email(message = "Email khong hop le",regexp="^[a-zA-Z0-9_!#$%&'*+/=?^{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @StrongPassword(message = "Pass phai co toi thieu 8 ky tu")
    private String password;
    private String confirmPassword;
}
