package com.example.springboot.Message;

import java.util.Date;

public class Message {
    private String message_id;
    private String chat_id;
    private Date send_time;
    private String sender_id;
    private String messageContent;

    public Message(String message_id, String chat_id, Date send_time, String sender_id, String messageContent)
    {
        this.message_id = message_id;
        this.chat_id = chat_id;
        this.send_time = send_time;
        this.sender_id = sender_id;
        this.messageContent = messageContent;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
