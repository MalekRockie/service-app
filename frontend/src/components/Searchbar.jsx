import {Box, IconButton, InputAdornment, TextField} from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import FilterListIcon from '@mui/icons-material/FilterList';

const SearchBar = () => {
  return (
    <Box sx={{ display: "flex", justifyContent: "center" }}>
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
        sx={{
          marginTop: 2,
          marginBottom: 2,
          backgroundColor: "#FFFFFF",
          borderRadius: "20px",
          width: "80%",
          maxWidth: "600px"
        }}
      />
      <IconButton
        sx={{
          marginTop: "15px", marginLeft: 1, color: "#FFFFFF", borderRadius: "20px" }}>
        <FilterListIcon />
      </IconButton>
    </Box>
  );
}

export default SearchBar;