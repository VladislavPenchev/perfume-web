package com.penchev.perfume.service.impl;

import com.penchev.perfume.repository.RatingRepository;
import com.penchev.perfume.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
}
