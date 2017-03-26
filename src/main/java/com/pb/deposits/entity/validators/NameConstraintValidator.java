package com.pb.deposits.entity.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameConstraintValidator implements ConstraintValidator<Name, String> {

    @Override
    public void initialize(Name name) {
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
        return nameField != null && nameField.matches("[a-zA-Z \\.]*");
    }

}

