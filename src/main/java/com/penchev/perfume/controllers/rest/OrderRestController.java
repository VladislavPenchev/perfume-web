package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.models.binding.OrderBindingModel;
import com.penchev.perfume.models.entities.User;
import com.penchev.perfume.models.view.OrderViewModel;
import com.penchev.perfume.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderViewModel>> getOrders(Principal principal){
        return ResponseEntity.ok(orderService.getAllOrders(((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getEmail()));
    }

    @PostMapping("/order/{productName}")
    public ResponseEntity<OrderViewModel> confirmOrder(@RequestBody OrderBindingModel orderBindingModel,
                                                       @PathVariable String productName, Principal principal) {
        return new ResponseEntity<>(orderService.createOrder(orderBindingModel, productName,
                ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getEmail()), HttpStatus.CREATED);
    }

    @PutMapping("/order")
    public ResponseEntity<OrderViewModel> editQuantity(@RequestBody OrderBindingModel orderBindingModel, @RequestParam String orderId) {
        return ResponseEntity.ok(orderService.changeQuantity(orderBindingModel, orderId));
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
