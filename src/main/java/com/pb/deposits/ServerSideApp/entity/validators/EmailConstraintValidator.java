package com.pb.deposits.ServerSideApp.entity.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<Email, String> {

    @Override
    public void initialize(Email email) {
    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
        return emailField != null && emailField.matches("[a-zA-Z0-9()@ \\.]*");
    }

}