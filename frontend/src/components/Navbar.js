import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import ChatIcon from '@mui/icons-material/Chat';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import {IconButton} from "@mui/material";

const Navbar = () => {
  return (
    <AppBar position="static" sx={{ backgroundColor: "#00C314" }}>
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1 }}>
          ServiceMe
        </Typography>
          <IconButton href="/chat" aria-label="chat">
            <ChatIcon sx={{ color: "white" }} />
          </IconButton>
        <IconButton aria-label="options">
          <MoreVertIcon sx={{ color: "white" }}/>
        </IconButton>
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;