import { List, ListItem, ListItemText } from "@mui/material";

const ChatList = ({ chats, onChatSelect }) => {
  return (
    <List style={{ backgroundColor: '#2C2C2C', height: "100%", overflowY: "auto" }}>
      {chats.length > 0 ? (
        chats.map((chat) => (
          <ListItem button key={chat.id} onClick={() => onChatSelect(chat.id)}>
            <ListItemText primary={chat.partnerName} secondary={chat.lastMessage} />
          </ListItem>
        ))
      ) : (
        <ListItem>No Chats Available</ListItem>
      )}
    </List>
  );
};

export default ChatList;