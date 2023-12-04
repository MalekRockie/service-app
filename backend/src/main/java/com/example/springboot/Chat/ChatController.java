package com.example.springboot.Chat;

import com.example.springboot.Orders.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;


    @GetMapping("GetChats/{id}")
    public List<Chat> getChat(@PathVariable String id)
    {
        return chatService.getChat(id);
    }
}
