package com.example.springboot.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    public Reviews getReview(String id)
    {
        return reviewsRepository.getReview(id);
    }

    public Map<String, Object> getReviewsInfo(String service_provider_id)
    {
        return reviewsRepository.getReviewsInfo(service_provider_id);
    }

    public void createReview(Reviews newReview) {
        reviewsRepository.createReview(newReview);
    }

    public List<Reviews> getAllReviews(String id) {
        return reviewsRepository.getAllReviews(id);
    }
}
