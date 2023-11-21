package com.example.springboot.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;


    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("chat/GetMessages/{chat_id}")
    public List<Message> getMessages(@PathVariable String chat_id)
    {
        return messageService.getMessages(chat_id);
    }
}
