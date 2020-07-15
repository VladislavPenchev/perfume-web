package com.penchev.perfume.utils.impl;

import com.penchev.perfume.validator.ValidationGroups.Sequence;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorFormImpl {

    @Autowired
    private static Validator validator;

    @Autowired
    private static OrderValidationErrorsByFields orderValidationErrorsByFields;

    private ValidatorFormImpl() {
    }

    public static Set<String> validateForm(Object bindingModel) {

        Set<ConstraintViolation<Object>> cvErrors = validator.validate(bindingModel, Sequence.class);

        return orderValidationErrorsByFields.orderValidationError(cvErrors);
    }
}
