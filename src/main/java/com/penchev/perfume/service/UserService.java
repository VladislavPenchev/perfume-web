package com.penchev.perfume.service;

import com.penchev.perfume.models.binding.RegisterBindingModel;
import com.penchev.perfume.models.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {

    UserViewModel register(RegisterBindingModel registerBindingModel);

    Set<String> validateForm(Object bindingModel);

    UserViewModel getUserByEmail(String email);
}
