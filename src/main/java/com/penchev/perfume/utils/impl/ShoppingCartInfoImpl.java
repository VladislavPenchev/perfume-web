package com.penchev.perfume.utils.impl;

import com.penchev.perfume.models.view.OrderViewModel;
import com.penchev.perfume.utils.ShoppingCartInfo;

import java.math.BigDecimal;
import java.util.List;


public class ShoppingCartInfoImpl implements ShoppingCartInfo {

    private static final double DELIVERY_PRICE = 4.99;

    private List<OrderViewModel> orderViewModels;

    public ShoppingCartInfoImpl(List<OrderViewModel> orderViewModels) {
        this.orderViewModels = orderViewModels;
    }

    @Override
    public BigDecimal calculateAllProductPrice() {
        BigDecimal allProductPrice = BigDecimal.valueOf(0);

        for (OrderViewModel order : orderViewModels) {
            allProductPrice = allProductPrice.add(order.getOrderTotalPrice());
        }

        return allProductPrice;
    }

    @Override
    public BigDecimal calculateDeliveryPrice() {
        return this.calculateAllProductPrice().compareTo(BigDecimal.valueOf(50)) > 0 ?
                BigDecimal.valueOf(0) : BigDecimal.valueOf(DELIVERY_PRICE);
    }

    @Override
    public BigDecimal calculateRemainingPrice() {
        BigDecimal allProductPrice = this.calculateAllProductPrice();

        return allProductPrice.compareTo(BigDecimal.valueOf(50)) > 0 ?
                BigDecimal.valueOf(0) : BigDecimal.valueOf(50).subtract(allProductPrice);
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        BigDecimal allProductPrice = this.calculateAllProductPrice();

        return allProductPrice.compareTo(BigDecimal.valueOf(50)) > 0 ?
                allProductPrice : BigDecimal.valueOf(4.99).add(allProductPrice);
    }

    @Override
    public BigDecimal calculateSavePrice() {
        BigDecimal discountPrice = BigDecimal.valueOf(0);

        for(OrderViewModel order : orderViewModels) {
            discountPrice = discountPrice.add(order.getPriceWithoutDiscount().subtract(order.getPriceWithDiscount())
            .multiply(BigDecimal.valueOf(order.getQuantityOfProduct())));
        }

        return discountPrice;
    }

    private BigDecimal calculateDiscountPrice() {
        BigDecimal discountPrice = BigDecimal.valueOf(0);

        for(OrderViewModel order : orderViewModels) {
            discountPrice = discountPrice.add(order.getPriceWithDiscount());
        }

        return discountPrice;
    }

}
