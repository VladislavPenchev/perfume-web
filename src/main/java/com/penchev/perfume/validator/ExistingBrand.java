package com.penchev.perfume.validator;

import com.penchev.perfume.models.entities.Brand;
import com.penchev.perfume.repository.BrandRepository;
import com.penchev.perfume.validate.ExistBrand;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingBrand implements ConstraintValidator<ExistBrand, String> {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        Brand brand = brandRepository.findByName(name)
                .orElse(null);

        return brand == null || !brand.getName().equals(name);
    }
}
