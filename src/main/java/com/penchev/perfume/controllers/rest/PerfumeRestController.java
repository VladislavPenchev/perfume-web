package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.models.binding.PerfumeBindingModel;
import com.penchev.perfume.models.entities.Perfume;
import com.penchev.perfume.models.view.PerfumeViewModel;
import com.penchev.perfume.models.view.ProductViewModel;
import com.penchev.perfume.service.PerfumeService;
import com.penchev.perfume.validator.ValidationGroups.Sequence;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PerfumeRestController {

    @Autowired
    private PerfumeService perfumeService;

    @GetMapping("/perfumes/{id}")
    public ResponseEntity<PerfumeViewModel> getPerfume(@PathVariable String id) {
        PerfumeViewModel perfumeViewModel = this.perfumeService.getOneProduct(id);

        return ResponseEntity.ok(perfumeViewModel);
    }

    @GetMapping("/perfumes")
    public ResponseEntity<List<PerfumeViewModel>> getAllPerfumes() {
        return ResponseEntity.ok(perfumeService.getAllProducts());
    }

    @GetMapping("/perfumes/categories")
    public ResponseEntity<List<PerfumeViewModel>> allPerfumesByCategory(@RequestParam String category) {
        return ResponseEntity.ok(perfumeService.getAllPerfumesByCategories(category));
    }

    @GetMapping("/perfumes/lowest-price")
    public ResponseEntity<List<PerfumeViewModel>> allPerfumesByLowestPrice(@RequestParam String category) {
        return ResponseEntity.ok(perfumeService.getAllPerfumesByCategoryAndLowestPrice(category));
    }

    @PostMapping("/perfumes")
    public ResponseEntity<Perfume> confirmPerfume(@RequestBody @Validated({Sequence.class}) PerfumeBindingModel perfumeBindingModel) {

        ProductViewModel productViewModel = perfumeService.createProduct(perfumeBindingModel);

        return new ResponseEntity(productViewModel, HttpStatus.CREATED);
    }

    @PutMapping("/perfumes/{id}")
    public ResponseEntity<PerfumeViewModel> editProduct(@PathVariable String id, @RequestBody @Validated({Sequence.class}) PerfumeBindingModel perfumeBindingModel) {


        return ResponseEntity.ok(perfumeService.editPerfumeViewModel(id, perfumeBindingModel));
    }

    @DeleteMapping("/perfumes/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        perfumeService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

}
