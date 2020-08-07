package com.penchev.perfume.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ratings")
@EqualsAndHashCode(callSuper = true)
public class Rating extends BaseEntity {
    private int stars;

    private String opinion;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_id")
    private String productId;
}
