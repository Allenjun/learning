package com.allen.learningbootvalidation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PhoneNumberConstraintor implements ConstraintValidator<PhoneNumber, String> {

    private String regep = null;

    public void initialize(PhoneNumber constraint) {
        this.regep = constraint.regep();
    }

    public boolean isValid(String obj, ConstraintValidatorContext context) {
        return Objects.nonNull(obj) && obj.matches(regep);
    }
}
