package com.example.springboot.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;


    @GetMapping("review/GetReview/{id}")
    public Reviews getReview(@PathVariable String id)
    {
        return reviewsService.getReview(id);
    }


}
