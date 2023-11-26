import {Box, TextField, Button, InputAdornment} from '@mui/material';
import {useState} from "react";

const ChatWindow = ({ messages }) => {
  const [newMessage, setNewMessage] = useState('');

  const handleSendMessage = () => {
    // Logic to send message
    setNewMessage('');
  };

  return (
    <Box style={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
      <Box style={{ flexGrow: 1, overflowY: 'auto' }}>
        {messages.length > 0 ? (
          messages.map((msg, index) => (
            <div key={index}>{msg.text}</div>
          ))
        ) : (
          <div>No Messages</div>
        )}
      </Box>
      <Box>
        <TextField
          fullWidth
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          placeholder="Type a message..."
          InputProps={{
            style: { color: 'white', borderColor: 'white' },
            endAdornment: (
              <InputAdornment position="end">
                <Button onClick={handleSendMessage}>Send</Button>
              </InputAdornment>
            ),
          }}
        />
      </Box>
    </Box>
  );
};

export default ChatWindow;