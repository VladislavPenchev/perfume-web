package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.models.binding.RatingBindingModel;
import com.penchev.perfume.models.view.RatingViewModel;
import com.penchev.perfume.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingRestController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/ratings")
    public ResponseEntity<List<RatingViewModel>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/ratings/{productName}")
    public ResponseEntity<RatingViewModel> getRating(@PathVariable String productName) {
        return ResponseEntity.ok(ratingService.getOneRatingByProductName(productName));
    }

    @PostMapping("/ratings/{productName}")
    public ResponseEntity<RatingViewModel> confirmRating(@RequestBody RatingBindingModel ratingBindingModel, Principal principal,
                                                         @PathVariable String productName){
        return new ResponseEntity<>(ratingService.createRating(ratingBindingModel, principal.getName(), productName), HttpStatus.CREATED);
    }

    @DeleteMapping("/ratings/{productName}/{username}")
    public ResponseEntity<Void> deleteRating(@PathVariable String productName, @PathVariable String username){
        ratingService.deleteRating(productName);

        return ResponseEntity.noContent().build();
    }

}
