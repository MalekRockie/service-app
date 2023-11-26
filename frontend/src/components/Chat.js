import ChatList from './ChatList';
import ChatWindow from './ChatWindow';
import {useState} from "react";
import Navbar from "./Navbar";

const Chat = () => {
  const [selectedChat, setSelectedChat] = useState(null);
  const [chats, setChats] = useState([]); // Replace with chat data or keep it empty for testing
  const [messages, setMessages] = useState([]); // Replace with messages data or keep it empty for testing

  const handleChatSelect = (chatId) => {
    setSelectedChat(chatId);
    // Load messages for the selected chat
  };

  return (
    <>
      <Navbar />
      <div style={{ display: "flex", height: "calc(100vh - 80px)" }}> {/* Adjust for navbar height */}
        <div style={{ width: "15%" }}>
          <ChatList chats={chats} onChatSelect={handleChatSelect} />
        </div>
        <div style={{ width: "85%" }}>
          <ChatWindow messages={selectedChat ? messages : []} />
        </div>
      </div>
    </>
  );
};

export default Chat;