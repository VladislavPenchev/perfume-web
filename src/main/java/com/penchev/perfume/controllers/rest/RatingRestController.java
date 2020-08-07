package com.penchev.perfume.controllers.rest;

import com.penchev.perfume.models.binding.RatingBindingModel;
import com.penchev.perfume.models.entities.User;
import com.penchev.perfume.models.view.RatingViewModel;
import com.penchev.perfume.service.RatingService;
import com.penchev.perfume.validator.ValidationGroups.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RatingRestController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/ratings/{productName}")
    public ResponseEntity<List<RatingViewModel>> getRating(@PathVariable String productName) {
        return ResponseEntity.ok(ratingService.getAllRatingsByProductName(productName));
    }

    @PostMapping("/ratings/{productName}")
    public ResponseEntity<RatingViewModel> confirmRating(@RequestBody @Validated({Sequence.class}) RatingBindingModel ratingBindingModel, Principal principal,
                                                         @PathVariable String productName) {
        return new ResponseEntity<>(ratingService.createRating(ratingBindingModel,
                ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getEmail(), productName), HttpStatus.CREATED);
    }

    @DeleteMapping("/ratings/{productName}/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable String productName, @PathVariable String ratingId, Principal principal) {
        ratingService.deleteRating(productName, principal.getName(), ratingId);
        return ResponseEntity.noContent().build();
    }
}
