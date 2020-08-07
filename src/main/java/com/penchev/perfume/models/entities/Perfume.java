package com.penchev.perfume.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "perfumes")
@EqualsAndHashCode(callSuper = true)
@ToString
public class Perfume extends Product {
    @Column(name = "aroma_combination", length = 500, columnDefinition = "text")
    private String aromaCombination;

    @Column(name = "has_wrap")
    private boolean hasWrap;

    public Perfume(String name, BigDecimal price, String description, String videoUrl, int discount, String ean, int quantity, String aromaCombination, boolean hasWrap, String categoryId, String brandId) {
        super(name, price, description, videoUrl, discount, ean, quantity, brandId, categoryId);
        this.aromaCombination = aromaCombination;
        this.hasWrap = hasWrap;
    }
}
