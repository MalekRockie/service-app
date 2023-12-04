import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import Navbar from "./Navbar";
import { Card, CardContent, Typography, Box, Rating } from "@mui/material";

const Reviews = () => {
  const [reviews, setReviews] = useState([]);
  const { id } = useParams();

  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const reviewsResponse = await axios.get(`http://localhost:8080/reviews/GetAllReviewsForProvider/${id}`);
        if (Array.isArray(reviewsResponse.data)) {
          const userRequests = reviewsResponse.data.map(review =>
            axios.get(`http://localhost:8080/getUser/${review.user_id}`)
          );
          const userResponses = await Promise.all(userRequests);
          const combinedReviews = reviewsResponse.data.map((review, index) => ({
            ...review,
            author: userResponses[index].data
          }));
          setReviews(combinedReviews);
        } else {
          console.error("Received data is not an array:", reviewsResponse.data);
        }
      } catch (error) {
        console.error("Error fetching reviews:", error);
      }
    };

    fetchReviews();
  }, [id]);

  return (
    <div>
      <Navbar />
      <Box sx={{ padding: 3 }}>
        <Typography variant="h4" sx={{ marginBottom: 2 }}>Reviews</Typography>
        {reviews.map((review, index) => (
          <Card key={index} sx={{ marginBottom: 2 }}>
            <CardContent>
              <Typography variant="h6">Author: {review.author.first_name} {review.author.last_name}</Typography>
              <Typography variant="body1">Review: {review.description}</Typography>
              <Box>
                <Rating value={review.stars} readOnly />
              </Box>
            </CardContent>
          </Card>
        ))}
      </Box>
    </div>
  );
}

export default Reviews;
