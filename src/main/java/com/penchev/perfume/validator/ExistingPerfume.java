package com.penchev.perfume.validator;


import com.penchev.perfume.models.entities.Perfume;
import com.penchev.perfume.models.entities.User;
import com.penchev.perfume.repository.PerfumeRepository;
import com.penchev.perfume.validate.ExistPerfume;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingPerfume implements ConstraintValidator<ExistPerfume, String> {

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Override
    public void initialize(ExistPerfume constraintAnnotation) {

    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        Perfume perfume = perfumeRepository.findByName(name).orElse(null);

        if(perfume != null) {
            return !perfume.getName().equals(name);
        }

        return true;
    }
}
