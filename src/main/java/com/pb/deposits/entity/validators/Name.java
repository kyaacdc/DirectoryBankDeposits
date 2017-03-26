package com.pb.deposits.entity.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    String message() default "{Name}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
