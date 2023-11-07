import {Typography, Button, Avatar, Rating} from "@mui/material";
import StarIcon from '@mui/icons-material/Star';
import axios from "axios";
import {useEffect, useState} from "react";
import Navbar from "./Navbar";
import {useParams} from "react-router-dom";

const ServiceProviderProfile = () => {
  const [provider, setProvider] = useState(null);
  const { id } = useParams();

  useEffect(() => {
    const fetchServiceProvider = async () => {
      try {
        const response = await axios.get(`http://localhost:5000/service-providers/${id}`);
        setProvider(response.data);
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
          height: "calc(100vh - 64px)" }}
      >
        <Avatar sx={{ width: 60, height: 60, backgroundColor: "#450B8F" }}>{provider.avatar}</Avatar>
        <Typography variant="h5" sx={{ marginTop: "1rem" }}>{provider.name}</Typography>

        <Typography variant="body1" sx={{ marginTop: "0.5rem" }}>
          <Rating
            value={provider.rating}
            readOnly
            emptyIcon={<StarIcon sx={{ color: "#FFFFFF" }} />}
          />
          ({provider.reviewCount} reviews)
        </Typography>
        <Button variant="contained" color="inherit" sx={{ marginTop: "1rem", backgroundColor: "#00C314" }}>
          Request Service
        </Button>
      </div>
    </div>
  );
};

export default ServiceProviderProfile;