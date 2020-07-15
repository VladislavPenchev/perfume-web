package com.penchev.perfume.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity(name = "brands")
@EqualsAndHashCode(callSuper = true)
public class Brand extends BaseEntity {
    private String name;
}
