package com.penchev.perfume.service.impl;

import com.penchev.perfume.constants.ExceptionConstants;
import com.penchev.perfume.exception.exceptions.ProductNotFoundException;
import com.penchev.perfume.exception.exceptions.RatingNotFoundException;
import com.penchev.perfume.models.binding.RatingBindingModel;
import com.penchev.perfume.models.entities.Product;
import com.penchev.perfume.models.entities.Rating;
import com.penchev.perfume.models.entities.User;
import com.penchev.perfume.models.view.RatingViewModel;
import com.penchev.perfume.repository.ProductRepository;
import com.penchev.perfume.repository.RatingRepository;
import com.penchev.perfume.repository.UserRepository;
import com.penchev.perfume.service.RatingService;
import com.penchev.perfume.utils.UserNamesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Date;
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

    @Override
    public List<RatingViewModel> getAllRatingsByProductName(String productName) {
        Product product = checkIfProductExist(productName);

        return ratingRepository.findAllByProductIdAndIsActive(product.getId(), true)
                .stream()
                .map(this::convertRatingToRatingViewModel)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public RatingViewModel createRating(RatingBindingModel ratingBindingModel, String userName, String productName) {
        User user = checkIfUserExist(userName);

        Product product = checkIfProductExist(productName);

        boolean checkIfUserRated = ratingRepository.findByUserIdAndProductIdAndIsActive(user.getId(), product.getId(), true).isPresent();
        if (checkIfUserRated) {
            throw new ValidationException(ExceptionConstants.USER_RATED);
        }

        Rating rating = Rating.builder()
                .stars(ratingBindingModel.getStars())
                .opinion(ratingBindingModel.getOpinion())
                .userId(user.getId())
                .productId(product.getId())
                .isActive(true)
                .createdTimestamp(new Date())
                .updatedTimestamp(new Date())
                .version(0)
                .build();
        rating = ratingRepository.save(rating);

        return convertRatingToRatingViewModel(rating);
    }

    @Override
    public void deleteRating(String productName, String userName, String ratingId) {
        User user = checkIfUserExist(userName);

        Product product = checkIfProductExist(productName);

        Rating rating = ratingRepository.findByUserIdAndProductIdAndIsActive(user.getId(), product.getId(), true)
                .orElseThrow(() -> new RatingNotFoundException(String.format(ExceptionConstants.NOT_FOUND_RATING_WITH_USER_ID_PRODUCT_ID, user.getId(), product.getId())));
        rating.setActive(false);
        ratingRepository.save(rating);
    }

    private User checkIfUserExist(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(ExceptionConstants.NOT_FOUND_USER_WITH_EMAIL, userEmail)));
    }

    private User checkIfUserExistById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(ExceptionConstants.NOT_FOUND_USER_WITH_ID, userId)));
    }

    private Product checkIfProductExist(String productName) {
        return productRepository.findByNameAndIsActive(productName, true)
                .orElseThrow(() -> new ProductNotFoundException(String.format(ExceptionConstants.NOT_FOUND_PRODUCT_WITH_NAME, productName)));
    }

    private RatingViewModel convertRatingToRatingViewModel(Rating rating) {
        User user = checkIfUserExistById(rating.getUserId());

        return RatingViewModel.builder()
                .id(rating.getId())
                .stars(rating.getStars())
                .opinion(rating.getOpinion())
                .userName(UserNamesUtil.concatUserNames(user.getFirstName(), user.getLastName()))
                .build();
    }
}
