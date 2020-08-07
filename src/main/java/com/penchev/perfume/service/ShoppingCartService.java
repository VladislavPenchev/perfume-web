package com.penchev.perfume.service;

import com.penchev.perfume.models.view.ShoppingCartViewModel;

public interface ShoppingCartService {

    ShoppingCartViewModel createShoppingCart(String userEmail);

    ShoppingCartViewModel getShoppingCart(String userEmail);
}
