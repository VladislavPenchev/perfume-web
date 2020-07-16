package com.penchev.perfume.service;

import com.penchev.perfume.models.binding.RatingBindingModel;
import com.penchev.perfume.models.view.RatingViewModel;

import java.util.List;

public interface RatingService {
    List<RatingViewModel> getAllRatings();

    RatingViewModel getOneRatingByProductName(String productName);

    RatingViewModel createRating(RatingBindingModel ratingBindingModel, String userName, String productName);

    void deleteRating(String productName);
}
