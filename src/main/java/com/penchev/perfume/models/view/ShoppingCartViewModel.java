package com.penchev.perfume.models.view;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ShoppingCartViewModel extends BaseViewModel{

    private List<OrderViewModel> orders;
    private BigDecimal allOrderPrice;
    private BigDecimal deliveryPrice;
    private BigDecimal remainingMoneyToDelivery;
    private BigDecimal totalPrice;
    private BigDecimal saveMoney;
}
