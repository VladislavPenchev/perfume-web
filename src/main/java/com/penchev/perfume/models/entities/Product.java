package com.penchev.perfume.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.math.BigDecimal;

@Data
@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product extends BaseEntity {
    private String name;

    private BigDecimal price;

    @Column(length = 500, columnDefinition = "text")
    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    private int discount;

    private String ean;

    private int quantity;

    @Column(name = "brand_id")
    private String brandId;

    @Column(name = "category_id")
    private String categoryId;


}
