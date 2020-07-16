package com.penchev.perfume.repository;

import com.penchev.perfume.models.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {

//    @Query("select r from com.penchev.perfume.models.entities.Rating r " +
//            "left join r.product p where p.name = :name")
//    Rating findRatingByProduct_Name(@Param("name") String name);
}
