package com.penchev.perfume.models.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    @Column(length = 500, columnDefinition = "text")
    private String description;
    private String videoUrl;
    private int discount;
    private String ean;
    private int qty;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Rating> ratings;
}
