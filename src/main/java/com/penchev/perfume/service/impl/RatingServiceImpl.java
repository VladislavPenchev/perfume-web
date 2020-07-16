package com.penchev.perfume.service.impl;

import com.penchev.perfume.constants.ExceptionConstants;
import com.penchev.perfume.exception.exceptions.ProductNotFoundException;
import com.penchev.perfume.models.binding.RatingBindingModel;
import com.penchev.perfume.models.entities.Product;
import com.penchev.perfume.models.entities.Rating;
import com.penchev.perfume.models.entities.User;
import com.penchev.perfume.models.view.RatingViewModel;
import com.penchev.perfume.repository.ProductRepository;
import com.penchev.perfume.repository.RatingRepository;
import com.penchev.perfume.repository.UserRepository;
import com.penchev.perfume.service.RatingService;
import com.penchev.perfume.utils.impl.UtilUserNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UtilUserNames utilUserNames;

    @Override
    public List<RatingViewModel> getAllRatings() {
        return ratingRepository.findAll()
                .stream()
                .map(this::convertRatingToRatingViewModel)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public RatingViewModel getOneRatingByProductName(String productName) {
        return null;
    }

    @Override
    public RatingViewModel createRating(RatingBindingModel ratingBindingModel, String userName, String productName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(ExceptionConstants.NOT_FOUND_USER_WITH_NAME, userName)));

        Rating rating = Rating.builder()
                .stars(ratingBindingModel.getStars())
                .opinion(ratingBindingModel.getOpinion())
                .user(user)
                .build();

//        Product product = productRepository.findByName(productName)
//                .orElseThrow(() -> new ProductNotFoundException(String.format(ExceptionConstants.NOT_FOUND_PRODUCT_WITH_NAME, productName)));
//        product.getRatings().add(rating);

        rating = ratingRepository.save(rating);

        return RatingViewModel.builder()
                .id(rating.getId())
                .stars(rating.getStars())
                .opinion(rating.getOpinion())
                .userName(utilUserNames.concatUserNames(rating.getUser().getFirstName(), rating.getUser().getLastName()))
                .build();
    }

    @Override
    public void deleteRating(String productName) {

    }

    private RatingViewModel convertRatingToRatingViewModel(Rating rating) {
        return RatingViewModel.builder()
                .id(rating.getId())
                .stars(rating.getStars())
                .opinion(rating.getOpinion())
                .userName(String.format("%s %s", rating.getUser().getFirstName(), rating.getUser().getLastName()))
                .build();
    }
}
