package com.penchev.perfume.service.impl;


import com.penchev.perfume.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.penchev.perfume.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        UserDetails userDetails = null;

        if(usernameOrEmail.contains("@")) {
            userDetails = this.userRepository.findByEmail(usernameOrEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        } else {
            userDetails = this.userRepository.findByUsername(usernameOrEmail)
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        }

        return userDetails;
    }
}
