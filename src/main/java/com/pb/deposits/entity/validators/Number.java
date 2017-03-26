package com.pb.deposits.entity.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = com.pb.deposits.entity.validators.NumberConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Number {
    String message() default "{Number}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

