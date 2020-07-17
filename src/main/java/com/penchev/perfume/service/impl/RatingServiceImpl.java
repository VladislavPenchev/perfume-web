package com.penchev.perfume.service.impl;

import com.penchev.perfume.constants.ExceptionConstants;
import com.penchev.perfume.exception.exceptions.ProductNotFoundException;
import com.penchev.perfume.exception.exceptions.UserNotFoundException;
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

import javax.validation.ValidationException;
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
    public List<RatingViewModel> getAllRatingsByProductName(String productName) {
        Product product = checkIfProductExist(productName);

        return product.getRatings()
                .stream()
                .map(this::convertRatingToRatingViewModel)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public RatingViewModel createRating(RatingBindingModel ratingBindingModel, String userName, String productName) {
        User user = checkIfUserExist(userName);

        Product product = checkIfProductExist(productName);

        boolean checkIfUserRated = product.getRatings()
                .stream()
                .anyMatch(r -> r.getUser().getEmail().equals(user.getEmail()));

        if (checkIfUserRated) {
            throw new ValidationException(ExceptionConstants.USER_RATED);
        }

        Rating rating = Rating.builder()
                .stars(ratingBindingModel.getStars())
                .opinion(ratingBindingModel.getOpinion())
                .user(user)
                .build();

        product.getRatings().add(rating);
        rating = ratingRepository.save(rating);

        return RatingViewModel.builder()
                .id(rating.getId())
                .stars(rating.getStars())
                .opinion(rating.getOpinion())
                .userName(utilUserNames.concatUserNames(rating.getUser().getFirstName(), rating.getUser().getLastName()))
                .build();
    }

    private User checkIfUserExist(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(ExceptionConstants.NOT_FOUND_USER_WITH_NAME, userName)));
    }

    private Product checkIfProductExist(String productName) {
        return productRepository.findByName(productName)
                .orElseThrow(() -> new ProductNotFoundException(String.format(ExceptionConstants.NOT_FOUND_PRODUCT_WITH_NAME, productName)));
    }

    @Override
    public void deleteRating(String productName, String username) {
        Product product = checkIfProductExist(productName);

        Rating rating = product.getRatings()
                .stream()
                .filter(r -> r.getUser().getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(String.format(ExceptionConstants.NOT_FOUND_USER_WITH_NAME, username)));


        product.getRatings().remove(rating);
        ratingRepository.delete(rating);
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
