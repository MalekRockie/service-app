package com.example.springboot.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getMessages(String chat_id) {
        return messageRepository.getMessages(chat_id);
    }
}
