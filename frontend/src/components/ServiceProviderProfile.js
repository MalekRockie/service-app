import {Typography, Button, Avatar, Rating, Box} from "@mui/material";
import StarIcon from '@mui/icons-material/Star';
import axios from "axios";
import {useEffect, useState} from "react";
import Navbar from "./Navbar";
import {Link, useParams} from "react-router-dom";

const ServiceProviderProfile = () => {
  const [provider, setProvider] = useState(null);
  const [averageRating, setAverageRating] = useState(0);
  const { id } = useParams();

  useEffect(() => {
    const fetchServiceProvider = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/service/GetProvider/${id}`);
        setProvider(response.data);

        const avgRatingResponse = await axios.get(`http://localhost:8080/reviews/GetAllReviews/${id}`);
        setAverageRating(avgRatingResponse.data);
      } catch (error) {
        console.error("Error fetching the service provider:", error);
      }
    };

    fetchServiceProvider();
  }, [id]);

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

        <Typography variant="body1" sx={{ marginTop: "0.5rem" }}>
          <Rating
            value={averageRating}
            readOnly
            emptyIcon={<StarIcon sx={{ color: "#FFFFFF" }} />}
          />
          <Link to={`/service-provider/${provider.service_provider_id}/reviews`} style={{ color: "inherit" }}>
            <Box sx={{ marginLeft: 1 }}>
              ({provider.reviewCount} reviews)
            </Box>
          </Link>
        </Typography>
        <Button variant="contained" color="inherit" sx={{ marginTop: "1rem", backgroundColor: "#00C314" }}>
          Request Service
        </Button>
      </div>
    </div>
  );
};

export default ServiceProviderProfile;