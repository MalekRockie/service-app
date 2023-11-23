package com.example.springboot.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    public Reviews getReview(String id)
    {
        return reviewsRepository.getReview(id);
    }
}
