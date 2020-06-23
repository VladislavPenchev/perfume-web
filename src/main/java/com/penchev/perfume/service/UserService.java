package com.penchev.perfume.service;

import com.penchev.perfume.models.binding.RegisterBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {

    boolean register(RegisterBindingModel registerBindingModel);

    Set<String> validateForm(Object bindingModel);
}
