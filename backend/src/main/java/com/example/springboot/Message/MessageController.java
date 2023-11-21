package com.example.springboot.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("chat/SendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody Message msg) {
        messageService.sendMessage(msg);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
