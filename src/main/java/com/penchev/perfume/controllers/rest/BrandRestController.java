package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BrandRestController {

    @Autowired
    private BrandService brandService;
}
