package com.penchev.perfume.service.impl;

import com.penchev.perfume.constants.ExceptionConstants;
import com.penchev.perfume.exception.exceptions.ShoppingCartNotFoundException;
import com.penchev.perfume.exception.exceptions.UserNotFoundException;
import com.penchev.perfume.models.entities.ShoppingCart;
import com.penchev.perfume.models.entities.User;
import com.penchev.perfume.models.view.ShoppingCartViewModel;
import com.penchev.perfume.repository.ShoppingCartRepository;
import com.penchev.perfume.repository.UserRepository;
import com.penchev.perfume.service.OrderService;
import com.penchev.perfume.service.ShoppingCartService;
import com.penchev.perfume.utils.ShoppingCartInfo;
import com.penchev.perfume.utils.impl.ShoppingCartInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public ShoppingCartViewModel getShoppingCart(String userEmail) {
        User user = checkIfUserExist(userEmail);

        ShoppingCartInfo shoppingCartInfo = new ShoppingCartInfoImpl(orderService.getAllOrders(userEmail));

        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ShoppingCartNotFoundException(String.format(ExceptionConstants.NOT_FOUND_SHOPPING_CART_WITH_USER_ID, user.getId())));

        return ShoppingCartViewModel.builder()
                .id(shoppingCart.getId())
                .orders(orderService.getAllOrders(userEmail))
                .allOrderPrice(shoppingCartInfo.calculateAllProductPrice())
                .deliveryPrice(shoppingCartInfo.calculateDeliveryPrice())
                .remainingMoneyToDelivery(shoppingCartInfo.calculateRemainingPrice())
                .totalPrice(shoppingCartInfo.calculateTotalPrice())
                .saveMoney(shoppingCartInfo.calculateSavePrice())
                .build();
    }

    @Override
    public ShoppingCartViewModel createShoppingCart(String userEmail) {
        User user = checkIfUserExist(userEmail);

        if(checkIfShoppingCartExist(user.getId())) {
            throw new IllegalArgumentException("Can not create shopping cart because it exists");
        }

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(user.getId())
                .isActive(true)
                .createdTimestamp(new Date())
                .updatedTimestamp(new Date())
                .version(0)
                .build();

        shoppingCart = shoppingCartRepository.save(shoppingCart);

        return ShoppingCartViewModel.builder()
                .id(shoppingCart.getId())
                .orders(orderService.getAllOrders(userEmail))
                .build();
    }

    private User checkIfUserExist(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(String.format(ExceptionConstants.NOT_FOUND_USER_WITH_EMAIL, userEmail)));
    }

    private boolean checkIfShoppingCartExist(String userId) {
        return shoppingCartRepository.findByUserId(userId).isPresent();
    }
}
