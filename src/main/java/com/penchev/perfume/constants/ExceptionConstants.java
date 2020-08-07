package com.penchev.perfume.constants;

public final class ExceptionConstants {

    private ExceptionConstants() {
    }

    //users
    public static final String NOT_FOUND_USER_WITH_NAME = "Not Found User with name: %s";
    public static final String NOT_FOUND_USER_WITH_EMAIL = "Not Found User with email: %s";
    public static final String NOT_FOUND_USER_WITH_ID = "Not Found User with id: %s";

    //products
    public static final String NOT_FOUND_PRODUCT_WITH_ID = "Not Found Product with id: %s";
    public static final String NOT_FOUND_PRODUCT_WITH_NAME = "Not Found Product with name: %s";

    //categories
    public static final String NOT_FOUND_CATEGORY_WITH_NAME = "Not Found Category with name: %s";
    public static final String NOT_FOUND_CATEGORY_WITH_ID = "Not Found Category with id: %s";

    //brands
    public static final String NOT_FOUND_BRAND_WITH_NAME = "Not Found Brand with name: %s";
    public static final String NOT_FOUND_BRAND_WITH_ID = "Not Found Brand with id: %s";

    //orders
    public static final String NOT_FOUND_ORDER_WITH_ORDER_ID = "Not Found Order with order id: %s";

    //ratings
    public static final String NOT_FOUND_RATING_WITH_USER_ID_PRODUCT_ID = "Not Found Rating with user id: %s and product id: %s";
    public static final String USER_RATED = "Can not rate about this product for second time!";

    //shopping cart
    public static final String NOT_FOUND_SHOPPING_CART_WITH_USER_ID = "Not Found Shopping cart with user id: %s";

}
