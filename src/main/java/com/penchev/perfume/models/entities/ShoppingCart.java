package com.penchev.perfume.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity(name = "shopping_cards")
@EqualsAndHashCode(callSuper = true)
public class ShoppingCart extends BaseEntity {

    @Column(name = "user_id")
    private String userId;
}
