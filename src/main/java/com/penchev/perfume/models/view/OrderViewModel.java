package com.penchev.perfume.models.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class OrderViewModel extends BaseViewModel{
    private int qtyOfProduct;

    private String productName;

    private BigDecimal price;
}
