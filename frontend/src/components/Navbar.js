import {AppBar, IconButton, Menu, MenuItem, Toolbar, Typography} from "@mui/material";
import ChatIcon from "@mui/icons-material/Chat";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import {Link} from "react-router-dom";
import {useState} from "react";


const Navbar = () => {
  const [anchorEl, setAnchorEl] = useState(null);
  // dummy id
  const serviceProviderId = "123";

  const handleMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
  };

  return (
    <AppBar position="static" sx={{ backgroundColor: "#00C314" }}>
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
          ServiceMe
        </Typography>
          <IconButton href="/chat" aria-label="chat">
            <ChatIcon sx={{ color: "white" }} />
          </IconButton>
        <IconButton aria-label="options" onClick={handleMenuOpen}>
          <MoreVertIcon sx={{ color: "white" }}/>
        </IconButton>
        <Menu
          anchorEl={anchorEl}
          open={Boolean(anchorEl)}
          onClose={handleMenuClose}
        >
          <MenuItem onClick={handleMenuClose} component={Link} to={`/service-provider/${serviceProviderId}/orders`}>
            Orders
          </MenuItem>
        </Menu>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;