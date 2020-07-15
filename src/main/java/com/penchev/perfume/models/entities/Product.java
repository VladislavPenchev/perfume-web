package com.penchev.perfume.models.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Product extends BaseEntity {
    private String name;
    private BigDecimal price;
    @Column(length = 500, columnDefinition = "text")
    private String description;
    private String videoUrl;
    private int discount;
    private String ean;
    private int qty;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<Rating> ratings;
}
