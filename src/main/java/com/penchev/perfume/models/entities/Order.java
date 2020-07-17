package com.penchev.perfume.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@SuperBuilder
@NoArgsConstructor
@Entity(name = "orders")
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity{
    private int qtyOfProduct;

    private String productId;

    private String userId;
}
