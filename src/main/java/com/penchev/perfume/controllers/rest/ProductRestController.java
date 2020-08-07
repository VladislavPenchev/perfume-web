package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.utils.ProductTypesUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    @GetMapping("/product-types")
    public ResponseEntity<List<String>> getProductTypes() {
        return new ResponseEntity<>(ProductTypesUtil.getAllProductTypes(), HttpStatus.OK);
    }
}
