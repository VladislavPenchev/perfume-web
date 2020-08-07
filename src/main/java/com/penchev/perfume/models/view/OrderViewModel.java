package com.penchev.perfume.models.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class OrderViewModel extends BaseViewModel{
    private String id;
    private String productName;
    private String productId;
    private int quantityOfProduct;
    private BigDecimal priceWithoutDiscount;
    private BigDecimal priceWithDiscount;
    private BigDecimal orderTotalPrice;
}
