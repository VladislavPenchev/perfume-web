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
@Entity(name = "orders")
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity {

    @Column(name = "quantity_of_product")
    private int quantityOfProduct;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "user_id")
    private String userId;
}
