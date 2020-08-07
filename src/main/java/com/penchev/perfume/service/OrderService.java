package com.penchev.perfume.service;

import com.penchev.perfume.models.binding.OrderBindingModel;
import com.penchev.perfume.models.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    OrderViewModel createOrder(OrderBindingModel orderBindingModel,String productName, String username);

    List<OrderViewModel> getAllOrders(String userEmail);

    void deleteOrder(String orderId);

    OrderViewModel changeQuantity(OrderBindingModel orderBindingModel, String orderId);
}
