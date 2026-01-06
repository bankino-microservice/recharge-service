package com.marouane.rechargeservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MoroccanPhoneValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface MoroccanPhone {

    String message() default "Invalid Moroccan phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
