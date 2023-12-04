import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Box, TextField, Button, Typography, Paper, List, ListItem } from '@mui/material';

const Chat = () => {
  const [chats, setChats] = useState([]);
  const [selectedChat, setSelectedChat] = useState(null);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState('');

  const userId = "071d4b08-57f7-4551-9b9c-e564dcdea1c7"; // Hardcoded user ID

  // Fetch chats when component mounts
  useEffect(() => {
    const fetchChats = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/GetChats/${userId}`);
        const chatData = response.data;

        // Fetch service provider details for each chat
        const chatsWithProviderInfo = await Promise.all(chatData.map(async (chat) => {
          const serviceProviderResponse = await axios.get(`http://localhost:8080/service/GetProvider/${chat.service_provider_id}`);
          return { ...chat, serviceProviderUsername: serviceProviderResponse.data.username };
        }));

        setChats(chatsWithProviderInfo);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchChats();
  }, []); // Empty dependency array to run only once on mount

  const selectChat = (chat_id) => {
    setSelectedChat(chat_id);
    setMessages([]);
  };

  const handleSendMessage = () => {
    if (!newMessage.trim() || !selectedChat) return;

    const messageData = {
      message_id: `temp_id_${new Date().getTime()}`,
      sender_id: userId,
      messageContent: newMessage
    };

    setMessages(prevMessages => [...prevMessages, messageData]);
    setNewMessage('');
  };

  return (
    <div>
      <Box sx={{ display: 'flex', height: '100vh' }}>
        {/* Chat List */}
        <List sx={{ width: '25%', bgcolor: 'black', overflowY: 'auto', color: 'white' }}>
          {chats.map((chat, index) => (
            <ListItem button key={index} onClick={() => selectChat(chat.chat_id)}>
              {chat.serviceProviderUsername || chat.chat_id}
            </ListItem>
          ))}
        </List>

        {/* Chat Messages */}
        <Box sx={{ flexGrow: 1, p: 2 }}>
          <Paper elevation={3} sx={{ p: 2, height: '70%', overflowY: 'auto', bgcolor: 'white' }}>
            {messages.map((message, index) => (
              <Box key={index} sx={{ display: 'flex', justifyContent: message.sender_id === userId ? 'flex-end' : 'flex-start', mb: 1 }}>
                <Box sx={{ bgcolor: message.sender_id === userId ? '#1976d2' : '#757575', color: 'white', borderRadius: '20px', padding: '10px 15px', maxWidth: '70%', wordBreak: 'break-word' }}>
                  <Typography variant="body1">{message.messageContent}</Typography>
                </Box>
              </Box>
            ))}
          </Paper>
          <Box sx={{ display: 'flex', mt: 2 }}>
            <TextField
              fullWidth
              multiline
              rows={2}
              variant="outlined"
              placeholder="Type a message"
              value={newMessage}
              onChange={(e) => setNewMessage(e.target.value)}
              sx={{ bgcolor: 'white', color: 'black' }}
            />
            <Button variant="contained" color="primary" onClick={handleSendMessage} sx={{ ml: 1 }}>
              Send
            </Button>
          </Box>
        </Box>
      </Box>
    </div>
  );
};

export default Chat;
