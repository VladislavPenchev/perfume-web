package com.penchev.perfume.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderInfoUtil {

    public static BigDecimal calculatePriceWithDiscount(BigDecimal price, int discount) {
        return calculateDiscountPrice(price, discount);
    }

    public static BigDecimal calculateOrderTotalPrice(BigDecimal price, int discount, int qty) {
        return qty > 1 ? calculateDiscountPrice(price, discount).multiply(new BigDecimal(qty))
                : calculateDiscountPrice(price, discount);
    }

    private static BigDecimal calculateDiscountPrice(BigDecimal price, int discount) {
        return discount > 0 ? price.subtract(calculateDiscount(price, discount)).setScale(2, RoundingMode.CEILING) : price;
    }

    private static BigDecimal calculateDiscount(BigDecimal price, int discount) {
        return (price.multiply(new BigDecimal(discount))).divide(new BigDecimal(100)).setScale(2, RoundingMode.CEILING);
    }
}
