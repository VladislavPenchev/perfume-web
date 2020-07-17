package com.penchev.perfume.service;

import com.penchev.perfume.models.binding.OrderBindingModel;
import com.penchev.perfume.models.view.OrderViewModel;

public interface OrderService {
    OrderViewModel createOrder(OrderBindingModel orderBindingModel, String username);
}
