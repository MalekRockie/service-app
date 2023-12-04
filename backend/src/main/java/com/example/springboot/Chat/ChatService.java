package com.example.springboot.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatService {

    @Autowired
    private ChatRepository chatrepository;
    public List<Chat> getChat(String id) {
        return chatrepository.getChat(id);
    }
}
