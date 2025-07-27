/*
 * @ (#) .java    1.0
 * Copyright (c)  IUH. All rights reserved.
 */
package com.example.LaptopShop.service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import javax.lang.model.element.Element;
import java.lang.annotation.*;

/*
 * @description
 * @author: Huu Thai
 * @date:
 * @version: 1.0
 */
@Constraint(validatedBy = RegisterValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterChecked {
    String message() default "User register validation failed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
