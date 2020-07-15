package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.models.binding.BrandBindingModel;
import com.penchev.perfume.models.view.BrandViewModel;
import com.penchev.perfume.service.BrandService;
import com.penchev.perfume.validator.ValidationGroups.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BrandRestController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public ResponseEntity<List<BrandViewModel>> getBrands(){
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/brands/{name}")
    public ResponseEntity<BrandViewModel> getBrand(@PathVariable String name) {
        return ResponseEntity.ok(brandService.getOneBrand(name));
    }

    @PostMapping("/brands")
    public ResponseEntity<BrandViewModel> confirmBrand(@RequestBody @Validated({Sequence.class})  BrandBindingModel brandBindingModel) {
        BrandViewModel brandViewModel = brandService.createBrand(brandBindingModel);
        return new ResponseEntity(brandViewModel, HttpStatus.CREATED);
    }

    @PutMapping("/brands/{name}")
    public ResponseEntity<BrandViewModel> editBrand(@PathVariable String name, @RequestBody @Validated({Sequence.class}) BrandBindingModel brandBindingModel) {
        return ResponseEntity.ok(brandService.editBrand(name, brandBindingModel));
    }

    @DeleteMapping("/brands/{name}")
    public ResponseEntity<Void> deleteBrand(@PathVariable String name) {
        brandService.deleteBrand(name);
        return ResponseEntity.noContent().build();
    }
}
