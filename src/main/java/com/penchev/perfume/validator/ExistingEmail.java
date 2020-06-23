package com.penchev.perfume.validator;

import com.penchev.perfume.models.entities.User;
import com.penchev.perfume.repository.UserRepository;
import com.penchev.perfume.validate.ExistEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistingEmail implements ConstraintValidator<ExistEmail, String> {


    private final UserRepository userRepository;

    @Autowired
    public ExistingEmail(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(ExistEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        User user = userRepository.findByEmail(email)
                .orElse(null);

        if(user != null) {
            return !user.getEmail().equals(email);
        }

        return true;
    }
}
