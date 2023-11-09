import { InputAdornment, TextField } from "@mui/material";
import Navbar from "./Navbar";
import SearchIcon from "@mui/icons-material/Search";
import {useEffect, useState} from "react";
import axios from "axios";


const ServiceBrowser = () => {
  const [providers, setProviders] = useState([]);

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
      <TextField
        size="small"
        placeholder="Search..."
        InputProps={{
          startAdornment: (
            <InputAdornment position="start">
              <SearchIcon />
            </InputAdornment>
          ),
        }}
        sx={{ marginTop: 2, marginBottom: 2, backgroundColor: "#FFFFFF", width: "50%", justifyContent: "center" }}
      />
    </div>
  );
}

export default ServiceBrowser;