package com.penchev.perfume.validator;

import com.penchev.perfume.models.entities.Category;
import com.penchev.perfume.models.entities.User;
import com.penchev.perfume.repository.CategoryRepository;
import com.penchev.perfume.validate.ExistCategory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingCategory implements ConstraintValidator<ExistCategory, String> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        Category category = categoryRepository.findByNameAndIsActive(name, true)
                .orElse(null);

        if(category != null) {
            return !category.getName().equals(name);
        }

        return true;
    }
}
