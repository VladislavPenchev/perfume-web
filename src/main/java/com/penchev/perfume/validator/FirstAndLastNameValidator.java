package com.penchev.perfume.validator;

import com.penchev.perfume.validate.ContainSpaceBetweenFirstAndLastNames;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstAndLastNameValidator implements ConstraintValidator<ContainSpaceBetweenFirstAndLastNames, String> {

    public void initialize(ContainSpaceBetweenFirstAndLastNames constraintAnnotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StringUtils.hasText(value)) {
            return value.split("\\s+").length == 2;
        }

        return true;
    }
}
