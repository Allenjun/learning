package com.allen.learningbootvalidation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = PhoneNumberConstraintor.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Inherited
public @interface PhoneNumber {

    String message() default "{com.allen.learningbootvalidation.validator.PhoneNumber.message}";

    String regep() default "^1[3-9]\\d{9}$";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
