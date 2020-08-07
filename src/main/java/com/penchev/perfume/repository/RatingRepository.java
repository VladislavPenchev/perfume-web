package com.penchev.perfume.repository;

import com.penchev.perfume.models.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {

    List<Rating> findAllByProductIdAndIsActive(String productId, boolean isActive);

    Optional<Rating> findByUserIdAndProductIdAndIsActive(String userId, String productId, boolean isActive);
}
