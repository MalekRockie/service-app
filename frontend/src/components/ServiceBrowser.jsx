import React, { useEffect, useState } from "react";
import axios from "axios";
import { Avatar, Box, List, ListItem, ListItemAvatar, ListItemText, Pagination, TextField, InputAdornment } from "@mui/material";
import Navbar from "./Navbar";
import { Link } from "react-router-dom";
import SearchIcon from "@mui/icons-material/Search";

const ServiceBrowser = () => {
  const itemsPerPage = 7;
  const [page, setPage] = useState(1);
  const [providers, setProviders] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    const fetchServiceProviders = async () => {
      try {
        const response = await axios.get("http://localhost:8080/service/GetProviders");
        const providerData = response.data;

        const serviceDetailsPromises = providerData.map(provider =>
          axios.get(`http://localhost:8080/Service/getServiceByProvider/${provider.serviceProviderId}`)
        );

        const serviceDetailsResponses = await Promise.all(serviceDetailsPromises);
        const serviceDetails = serviceDetailsResponses.map(response => response.data);

        const combinedProviders = providerData.map((provider, index) => ({
          ...provider,
          serviceName: serviceDetails[index].service_name,
        }));

        setProviders(combinedProviders);
      } catch (error) {
        console.error("Error fetching service providers:", error);
        setProviders([]);
      }
    };

    fetchServiceProviders();
  }, []);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const indexOfLastProvider = page * itemsPerPage;
  const indexOfFirstProvider = indexOfLastProvider - itemsPerPage;
  const currentProviders = providers
    .filter(provider => provider.username.toLowerCase().includes(searchTerm.toLowerCase()))
    .slice(indexOfFirstProvider, indexOfLastProvider);

  const getInitials = (firstName, lastName) => {
    return `${firstName[0]}${lastName[0]}`.toUpperCase();
  };

  return (
    <div>
      <Navbar />
      <Box sx={{ display: "flex", justifyContent: "center", padding: 2 }}>
        <TextField
          size="small"
          placeholder="Search..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          InputProps={{
            startAdornment: (
              <InputAdornment position="start">
                <SearchIcon />
              </InputAdornment>
            ),
          }}
          sx={{
            backgroundColor: "#FFFFFF",
            borderRadius: "20px",
            width: "80%",
            maxWidth: "600px"
          }}
        />
      </Box>
      <Box sx={{ padding: 2 }}>
        <List sx={{ width: "100%" }}>
          {currentProviders.map((provider) => (
            <Link to={`/service-provider-profile/${provider.serviceProviderId}`} key={provider.serviceProviderId} style={{ textDecoration: "none", color: "inherit" }}>
              <ListItem sx={{ backgroundColor: "#505050", marginBottom: 2, borderRadius: "10px", display: "flex", alignItems: "center" }}>
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#450B8F", width: 50, height: 50 }}>
                    {getInitials(provider.firstName, provider.lastName)}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary={provider.username} secondary={provider.city} sx={{ minWidth: "150px" }} />
                <Box sx={{ flex: 1, textAlign: "center" }}>
                  <ListItemText primary={`Service: ${provider.serviceName}`} />
                </Box>
              </ListItem>
            </Link>
          ))}
        </List>
        <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center", padding: 2 }}>
          <Pagination
            count={Math.ceil(providers.length / itemsPerPage)}
            page={page}
            onChange={handleChangePage}
            sx={{
              '& .MuiPaginationItem-root': {
                color: "#FFFFFF"
              },
              '& .MuiPaginationItem-icon': {
                color: "#FFFFFF"
              }
            }}
          />
        </Box>
      </Box>
    </div>
  );
};

export default ServiceBrowser;
