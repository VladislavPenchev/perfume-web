package com.penchev.perfume.service.impl;

import com.penchev.perfume.constants.ExceptionConstants;
import com.penchev.perfume.exception.exceptions.ProductNotFoundException;
import com.penchev.perfume.exception.exceptions.UserNotFoundException;
import com.penchev.perfume.models.binding.OrderBindingModel;
import com.penchev.perfume.models.entities.Order;
import com.penchev.perfume.models.entities.Product;
import com.penchev.perfume.models.entities.User;
import com.penchev.perfume.models.view.OrderViewModel;
import com.penchev.perfume.repository.OrderRepository;
import com.penchev.perfume.repository.ProductRepository;
import com.penchev.perfume.repository.UserRepository;
import com.penchev.perfume.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public OrderViewModel createOrder(OrderBindingModel orderBindingModel, String username) {
        User user = checkIfUserExist(username);

        Product product = checkIfProductExist(orderBindingModel);

        Order order = Order.builder()
                .qtyOfProduct(orderBindingModel.getQtyOfProduct())
                .productId(product.getId())
                .userId(user.getId())
                .build();

        order = orderRepository.save(order);

        return OrderViewModel.builder()
                .id(order.getId())
                .qtyOfProduct(order.getQtyOfProduct())
                .productName(product.getName())
                .price(product.getPrice())
                .build();

    }

    private Product checkIfProductExist(OrderBindingModel orderBindingModel) {
        return productRepository.findByName(orderBindingModel.getProductName())
                .orElseThrow(() -> new ProductNotFoundException(String.format(ExceptionConstants.NOT_FOUND_PRODUCT_WITH_NAME, orderBindingModel.getProductName())));
    }

    private User checkIfUserExist(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format(ExceptionConstants.NOT_FOUND_USER_WITH_NAME, username)));
    }
}
