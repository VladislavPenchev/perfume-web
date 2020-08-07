package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.models.entities.User;
import com.penchev.perfume.models.view.ShoppingCartViewModel;
import com.penchev.perfume.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class ShoppingCartRestController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/shopping-cart")
    public ResponseEntity<ShoppingCartViewModel> getShoppingCart(Principal principal) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCart((
                (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getEmail()));
    }

    @PostMapping("/shopping-cart")
    public ResponseEntity<ShoppingCartViewModel> confirmShoppingCart(Principal principal) {
        return new ResponseEntity<>(shoppingCartService.createShoppingCart((
                (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getEmail()), HttpStatus.CREATED);
    }
}
