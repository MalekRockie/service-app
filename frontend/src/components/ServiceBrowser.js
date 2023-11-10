import {Avatar, Box, List, ListItemAvatar, ListItemText, Pagination, Rating} from "@mui/material";
import Navbar from "./Navbar";
import {useEffect, useState} from "react";
import axios from "axios";
import SearchBar from "./SearchBar";
import StarIcon from "@mui/icons-material/Star";
import {Link} from "react-router-dom";


const ServiceBrowser = () => {
  const itemsPerPage = 7;
  const [page, setPage] = useState(1);
  const [providers, setProviders] = useState([]);

  const indexOfLastProvider = page * itemsPerPage;
  const indexOfFirstProvider = indexOfLastProvider - itemsPerPage;
  const currentProviders = providers.slice(indexOfFirstProvider, indexOfLastProvider);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  }

  useEffect(() => {
    const fetchServiceProviders = async () => {
      try {
        const response = await axios.get("http://localhost:5000/service-providers");
        setProviders(response.data);
      } catch (error) {
        console.error("Error fetching service providers:", error);
      }
    }

    fetchServiceProviders();
  }, []);

  return (
    <div>
      <Navbar />
      <SearchBar />
      <Box sx={{ padding: 2 }}>
        <List sx={{ width: "100%" }}>
          {currentProviders.map((provider) => (
          <Link to={`/service-provider-profile/${provider.id}`} key={provider.id} style={{ textDecoration: "none", color: "inherit" }}>
            <Box sx={{ marginBottom: 2, display: "flex", alignItems: "center" }}>
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#450B8F", width: "50px", height: "50px" }}>
                    {provider.avatar}
                  </Avatar>
                </ListItemAvatar>
              <Box sx={{ flexGrow: 1, backgroundColor: "#505050", display: "flex", alignItems: "center", px: "35px", py: "10px", borderRadius: "10px" }}>
                <ListItemText primary={provider.name} />
                <Box sx={{ display: "flex", alignItems: "center", marginLeft: "auto" }}>
                  <Rating
                    value={provider.rating}
                    readOnly
                    emptyIcon={<StarIcon sx={{ color: "#FFFFFF" }} />}
                  />
                  <Box sx={{ minWidth: "3em", marginLeft: 2}}>
                    ({provider.reviewCount})
                  </Box>
                </Box>
              </Box>
            </Box>
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
}

export default ServiceBrowser;