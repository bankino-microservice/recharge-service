package com.marouane.rechargeservice.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MoroccanPhoneValidator
        implements ConstraintValidator<MoroccanPhone, String> {

    private static final String MOROCCAN_PHONE_REGEX =
            "^(\\+212|212|0)(6|7)[0-9]{8}$";
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return value.matches(MOROCCAN_PHONE_REGEX);
    }
}
