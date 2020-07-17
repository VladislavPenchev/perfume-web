package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.models.binding.OrderBindingModel;
import com.penchev.perfume.models.view.OrderViewModel;
import com.penchev.perfume.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<OrderViewModel> confirmOrder(@RequestBody OrderBindingModel orderBindingModel,
                                                       Principal principal) {
        return new ResponseEntity(orderService.createOrder(orderBindingModel, principal.getName()), HttpStatus.CREATED);
    }
}
