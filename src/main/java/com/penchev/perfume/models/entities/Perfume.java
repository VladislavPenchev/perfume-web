package com.penchev.perfume.models.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "perfumes")
@EqualsAndHashCode(callSuper = true)
@ToString
public class Perfume extends Product {
    @Column(length = 500, columnDefinition = "text")
    private String aromaCombination;
    private boolean hasWrap;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Perfume(String name, BigDecimal price, String description, String videoUrl, int discount, String ean, int qty, String aromaCombination, boolean hasWrap, Category category, Brand brand, List<Rating> ratings) {
        super(name, price, description, videoUrl, discount, ean, qty, brand, ratings);
        this.aromaCombination = aromaCombination;
        this.hasWrap = hasWrap;
        this.category = category;
    }
}
