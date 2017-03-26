package com.pb.deposits.entity.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    String message() default "{Email}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
