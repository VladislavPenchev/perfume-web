package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.models.binding.RatingBindingModel;
import com.penchev.perfume.models.view.RatingViewModel;
import com.penchev.perfume.service.RatingService;
import com.penchev.perfume.validator.ValidationGroups.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<List<RatingViewModel>> getRating(@PathVariable String productName) {
        return ResponseEntity.ok(ratingService.getAllRatingsByProductName(productName));
    }

    @PostMapping("/ratings/{productName}")
    public ResponseEntity<RatingViewModel> confirmRating(@RequestBody @Validated({Sequence.class}) RatingBindingModel ratingBindingModel, Principal principal,
                                                         @PathVariable String productName){
        return new ResponseEntity<>(ratingService.createRating(ratingBindingModel, principal.getName(), productName), HttpStatus.CREATED);
    }

    @DeleteMapping("/ratings/{productName}")
    public ResponseEntity<Void> deleteRating(@PathVariable String productName, Principal principal){
        ratingService.deleteRating(productName, principal.getName());

        return ResponseEntity.noContent().build();
    }

}
