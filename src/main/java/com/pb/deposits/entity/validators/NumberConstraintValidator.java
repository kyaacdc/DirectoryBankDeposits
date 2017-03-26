package com.pb.deposits.entity.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberConstraintValidator implements ConstraintValidator<Number, String> {

    @Override
    public void initialize(Number number) {
    }

    @Override
    public boolean isValid(String numberField, ConstraintValidatorContext cxt) {
        if (numberField == null) {
            return false;
        }
        return numberField.matches("[0-9()-\\.]*");
    }
}

