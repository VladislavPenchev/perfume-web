package com.penchev.perfume.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

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
}
