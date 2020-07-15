package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.models.binding.CategoryBindingModel;
import com.penchev.perfume.models.view.CategoryViewModel;
import com.penchev.perfume.service.CategoryService;
import com.penchev.perfume.validator.ValidationGroups.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories/{name}")
    public ResponseEntity<CategoryViewModel> getCategory(@PathVariable String name) {
        return ResponseEntity.ok(categoryService.getOneCategoryByName(name));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryViewModel>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryViewModel> confirmCategory(@RequestBody @Validated({Sequence.class}) CategoryBindingModel categoryBindingModel) {

        CategoryViewModel categoryViewModel = categoryService.createCategory(categoryBindingModel);

        return new ResponseEntity(categoryViewModel, HttpStatus.CREATED);
    }

    @PutMapping("/categories/{name}")
    public ResponseEntity<CategoryViewModel> editCategory(@PathVariable String name, @RequestBody CategoryBindingModel categoryBindingModel) {
        CategoryViewModel categoryViewModel = categoryService.editCategory(name, categoryBindingModel);
        return ResponseEntity.ok(categoryViewModel);
    }

    @DeleteMapping("/categories/{name}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String name) {

        categoryService.deleteCategory(name);

        return ResponseEntity.noContent().build();
    }

}
