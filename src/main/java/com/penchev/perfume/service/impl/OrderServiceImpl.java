package com.penchev.perfume.service.impl;

import com.penchev.perfume.constants.ExceptionConstants;
import com.penchev.perfume.exception.exceptions.OrderNotFoundException;
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
import com.penchev.perfume.utils.OrderInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderViewModel> getAllOrders(String userEmail) {
        User user = checkIfUserExist(userEmail);

        return orderRepository.findAllByUserIdAndIsActive(user.getId(), true)
                .stream()
                .map(o -> {
                    Product product = checkIfProductExistById(o.getProductId());
                    return convertOrderToOrderViewModel(product, o);
                })
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void deleteOrder(String orderId) {
        Order order = orderRepository.findByIdAndIsActive(orderId, true)
                .orElseThrow(() -> new OrderNotFoundException(String.format(ExceptionConstants.NOT_FOUND_ORDER_WITH_ORDER_ID, orderId)));
        order.setActive(false);
        orderRepository.save(order);
    }

    @Override
    public OrderViewModel changeQuantity(OrderBindingModel orderBindingModel, String orderId) {
        Order order = orderRepository.findByIdAndIsActive(orderId, true)
                .orElseThrow(() -> new OrderNotFoundException(String.format(ExceptionConstants.NOT_FOUND_ORDER_WITH_ORDER_ID, orderId)));
        order.setQuantityOfProduct(orderBindingModel.getQuantityOfProduct());
        order = orderRepository.save(order);

        return convertOrderToOrderViewModel(checkIfProductExistById(order.getProductId()), order);
    }


    @Override
    public OrderViewModel createOrder(OrderBindingModel orderBindingModel,String productName, String username) {
        //TODO check with username not email
        User user = checkIfUserExist(username);

        Product product = checkIfProductExistByName(productName);

        Order order = Order.builder()
                .quantityOfProduct(orderBindingModel.getQuantityOfProduct())
                .productId(product.getId())
                .userId(user.getId())
                .isActive(true)
                .createdTimestamp(new Date())
                .updatedTimestamp(new Date())
                .version(0)
                .build();

        order = orderRepository.save(order);

        return convertOrderToOrderViewModel(product, order);
    }

    private Product checkIfProductExistById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(ExceptionConstants.NOT_FOUND_PRODUCT_WITH_ID, id)));
    }

    private OrderViewModel convertOrderToOrderViewModel(Product product, Order order) {
        return OrderViewModel.builder()
                .id(order.getId())
                .productName(product.getName())
                .productId(product.getId())
                .quantityOfProduct(order.getQuantityOfProduct())
                .priceWithoutDiscount(product.getPrice())
                .priceWithDiscount(OrderInfoUtil.calculatePriceWithDiscount(product.getPrice(), product.getDiscount()))
                .orderTotalPrice(OrderInfoUtil.calculateOrderTotalPrice(product.getPrice(), product.getDiscount(), order.getQuantityOfProduct()))
                .build();
    }

    private Product checkIfProductExistByName(String productName) {
        return productRepository.findByNameAndIsActive(productName, true)
                .orElseThrow(() -> new ProductNotFoundException(String.format(ExceptionConstants.NOT_FOUND_PRODUCT_WITH_NAME, productName)));
    }

    private User checkIfUserExist(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(String.format(ExceptionConstants.NOT_FOUND_USER_WITH_EMAIL, userEmail)));
    }
}
