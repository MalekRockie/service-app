package com.example.springboot.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public int getReviewsAvg(@PathVariable String id)
    {
        return reviewsService.getReviewsAvg(id);
    }

    @PostMapping("reviews/CreateReview")
        public ResponseEntity<?> createReview(@RequestBody Reviews newReview)
        {
            reviewsService.createReview(newReview);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
}
