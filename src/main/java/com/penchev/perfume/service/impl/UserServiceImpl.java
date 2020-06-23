package com.penchev.perfume.service.impl;


import com.penchev.perfume.models.binding.RegisterBindingModel;
import com.penchev.perfume.models.entities.User;
import com.penchev.perfume.repository.RoleRepository;
import com.penchev.perfume.repository.UserRepository;
import com.penchev.perfume.service.RoleService;
import com.penchev.perfume.service.UserService;
import com.penchev.perfume.utils.OrderValidationErrorsByFields;
import com.penchev.perfume.utils.SplitUserNames;
import com.penchev.perfume.validator.ValidationGroups.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SplitUserNames splitUserNames;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Validator validator;

    @Autowired
    private OrderValidationErrorsByFields orderValidationErrorsByFields;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found!"));
    }

    @Override
    public boolean register(RegisterBindingModel registerBindingModel) {
        this.roleService.seedRoles();
        splitUserNames.splitUserNames(registerBindingModel.getFirstAndLastNames());

        User user = null;
        try {
            user = User.builder()
                    .username("null")
                    .email(registerBindingModel.getEmail())
                    .password(bCryptPasswordEncoder.encode(registerBindingModel.getPassword()))
                    .firstName(splitUserNames.getFirstName())
                    .lastName(splitUserNames.getLastName())
                    .city(registerBindingModel.getCity())
                    .address(registerBindingModel.getAddress())
                    .postalCode(registerBindingModel.getPostalCode())
                    .phoneNumber(registerBindingModel.getPhone())
                    .authorities(roleService.addRole())
                    .build();

            this.userRepository.save(user);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Set<String> validateForm(Object bindingModel) {
//        return validator.validate(bindingModel)
//                .stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.toList());


        Set<ConstraintViolation<Object>> cvErrors = validator.validate(bindingModel, Sequence.class);
        
        return orderValidationErrorsByFields.orderValidationError(cvErrors);
    }
}
