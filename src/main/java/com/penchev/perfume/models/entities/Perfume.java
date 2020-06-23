package com.penchev.perfume.models.entities;

import lombok.*;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity(name = "perfumes")
@EqualsAndHashCode(callSuper = true)
public class Perfume extends Product {
    private String aromaCombination;
    private boolean hasWrap;
}
