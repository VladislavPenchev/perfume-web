package com.penchev.perfume.service.impl;

import com.penchev.perfume.models.entities.Role;
import com.penchev.perfume.repository.RoleRepository;
import com.penchev.perfume.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void seedRoles() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new Role("ROLE_USER"));
            this.roleRepository.saveAndFlush(new Role("ROLE_MODERATOR"));
            this.roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));
            this.roleRepository.saveAndFlush(new Role("ROLE_ROOT"));
        }
    }

    @Override
    public List<Role> addRole() {
        List<Role> roles = new ArrayList<>();

        roles.add(this.roleRepository.findByAuthority("ROLE_USER"));

        return Collections.unmodifiableList(roles);
    }

}
