package com.penchev.perfume.models.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    private String description;
    private String videoUrl;
    private int discount;
    private String ean;
    private int qty;
}
