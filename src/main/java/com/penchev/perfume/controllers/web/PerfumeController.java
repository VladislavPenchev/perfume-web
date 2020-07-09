package com.penchev.perfume.controllers.web;

import com.penchev.perfume.models.binding.PerfumeBindingModel;
import com.penchev.perfume.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    @GetMapping("/create-product")
    public ModelAndView createProduct(ModelAndView modelAndView) {
        PerfumeBindingModel perfumeBindingModel = new PerfumeBindingModel();
        modelAndView.addObject("productBindingModel", perfumeBindingModel);
        modelAndView.setViewName("product/create-product");
        return modelAndView;
    }

//    @PostMapping("/create-product")
//    public ModelAndView confirmProduct(@ModelAttribute PerfumeBindingModel perfumeBindingModel) {
//        perfumeService.createProduct(perfumeBindingModel);
//
//        return null;
//    }
}
