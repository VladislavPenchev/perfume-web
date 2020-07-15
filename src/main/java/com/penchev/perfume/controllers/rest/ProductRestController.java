package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.utils.ProductTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @Autowired
    private ProductTypes productTypes;

    @GetMapping("/product-types")
    public ResponseEntity<List<String>> getProductTypes() {
        return new ResponseEntity<>(productTypes.getAllProductTypes(), HttpStatus.OK);
    }
}
