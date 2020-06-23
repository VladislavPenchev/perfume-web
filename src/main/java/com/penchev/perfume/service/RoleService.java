package com.penchev.perfume.service;

import com.penchev.perfume.models.entities.Role;

import java.util.List;

public interface RoleService {

    void seedRoles();

    List<Role> addRole();

}
