package com.penchev.perfume.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity(name = "categories")
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {
    private String name;
}
