package com.example.springboot.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;


    @GetMapping("reviews/GetReview/{id}")
    public Reviews getReview(@PathVariable String id)
    {
        return reviewsService.getReview(id);
    }

    //This is to retrieve the average of all reviews for 1 service provider
    @GetMapping("reviews/GetAllReviews/{id}")
    public Map<String, Object> getReviewsInfo(@PathVariable String id)
    {
        return reviewsService.getReviewsInfo(id);
    }

    @PostMapping("reviews/CreateReview")
        public ResponseEntity<?> createReview(@RequestBody Reviews newReview)
        {
            reviewsService.createReview(newReview);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    @GetMapping("reviews/GetAllReviewsForProvider/{id}")
    public List<Reviews> getAllReviews(@PathVariable String id)
    {
        return reviewsService.getAllReviews(id);
    }
}
