package com.penchev.perfume.utils;

import java.math.BigDecimal;

public interface ShoppingCartInfo {

    BigDecimal calculateAllProductPrice();

    BigDecimal calculateDeliveryPrice();

    BigDecimal calculateRemainingPrice();

    BigDecimal calculateTotalPrice();

    BigDecimal calculateSavePrice();
}
