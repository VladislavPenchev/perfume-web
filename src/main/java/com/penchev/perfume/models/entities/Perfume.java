package com.penchev.perfume.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    public Perfume(String name, BigDecimal price, String description,
                   String videoUrl, int discount, String ean, int qty,
                   String aromaCombination, boolean hasWrap) {
        super(name, price, description, videoUrl, discount, ean, qty);
        this.aromaCombination = aromaCombination;
        this.hasWrap = hasWrap;
    }

}
