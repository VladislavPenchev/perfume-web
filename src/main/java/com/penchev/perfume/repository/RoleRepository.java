package com.penchev.perfume.repository;

import com.penchev.perfume.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByAuthority(String authority);
}
