package com.penchev.perfume.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

@Data
@Entity(name = "roles")
@NoArgsConstructor
@EqualsAndHashCode
public class Role extends BaseEntity implements GrantedAuthority {

    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
