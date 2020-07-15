package com.penchev.perfume.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "perfumes")
@EqualsAndHashCode(callSuper = true)
public class Perfume extends Product {
    @Column(length = 500, columnDefinition = "text")
    private String aromaCombination;
    private boolean hasWrap;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Perfume(String name, BigDecimal price, String description,
                   String videoUrl, int discount, String ean, int qty,
                   String aromaCombination, boolean hasWrap, Category category) {
        super(name, price, description, videoUrl, discount, ean, qty);
        this.aromaCombination = aromaCombination;
        this.hasWrap = hasWrap;
        this.category = category;
    }

}
