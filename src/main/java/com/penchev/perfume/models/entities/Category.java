package com.penchev.perfume.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "categories")
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Perfume> perfume;
}
