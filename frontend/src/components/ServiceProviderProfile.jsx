import React, { useEffect, useState } from "react";
import axios from "axios";
import { Typography, Button, Avatar, Rating, Box } from "@mui/material";
import StarIcon from '@mui/icons-material/Star';
import { useParams, Link } from "react-router-dom";
import Navbar from "./Navbar";
import { v4 as uuidv4 } from 'uuid';

const ServiceProviderProfile = () => {
  const [provider, setProvider] = useState(null);
  const [averageRating, setAverageRating] = useState(0);
  const [reviewCount, setReviewCount] = useState(0);
  const [isRequestMade, setIsRequestMade] = useState(false);
  const { id } = useParams();

  useEffect(() => {
    const fetchServiceProvider = async () => {
      try {
        const providerResponse = await axios.get(`http://localhost:8080/service/GetProvider/${id}`);
        setProvider(providerResponse.data);

        const reviewsResponse = await axios.get(`http://localhost:8080/reviews/GetAllReviews/${id}`);
        setAverageRating(reviewsResponse.data.averageRating || 0);
        setReviewCount(reviewsResponse.data.reviewCount);
      } catch (error) {
        console.error("Error fetching the service provider:", error);
      }
    };

    fetchServiceProvider();
  }, [id]);

  const handleRequestService = async () => {
    try {
      const orderId = uuidv4();
      const userId = "1";

      const serviceResponse = await axios.get(`http://localhost:8080/Service/getServiceByProvider/${id}`);
      if (!serviceResponse.data || !serviceResponse.data.service_id) {
        console.error("Service ID not found in the response");
        return;
      }

      const serviceId = serviceResponse.data.service_id;
      const orderDate = new Date().toISOString().split('T')[0];

      const requestData = {
        order_id: orderId,
        user_id: userId,
        order_date: orderDate,
        service_id: serviceId,
        status: "Pending"
      };

      await axios.post("http://localhost:8080/order/RequestService", requestData);
      alert("Service requested successfully!");
      setIsRequestMade(true);
    } catch (error) {
      console.error("Error requesting service:", error);
      alert("Failed to request service.");
    }
  };

  if (!provider) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <Navbar />
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
          height: "calc(100vh - 64px)"
        }}
      >
        <Avatar sx={{ width: 60, height: 60, backgroundColor: "#450B8F" }}>{provider.avatar}</Avatar>
        <Typography variant="h5" sx={{ marginTop: "1rem" }}>{provider.username}</Typography>

        <Typography component="div" variant="body1" sx={{ marginTop: "0.5rem" }}>
          <Rating
            value={averageRating}
            readOnly
            emptyIcon={<StarIcon sx={{ color: "#FFFFFF" }} />}
          />
          <Link to={`/service-provider/${id}/reviews`} style={{ color: "inherit" }}>
            <Box sx={{ marginLeft: 1 }}>
              ({reviewCount} reviews)
            </Box>
          </Link>
        </Typography>

        <Button 
          variant="contained" 
          color="inherit" 
          sx={{ marginTop: "1rem", backgroundColor: "#00C314" }}
          onClick={handleRequestService}
          disabled={isRequestMade}
        >
          Request Service
        </Button>
      </div>
    </div>
  );
};

export default ServiceProviderProfile;
