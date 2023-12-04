package com.example.springboot.Chat;

public class Chat {

    private String chat_id;
    private String user_id;
    private String service_provider_id;

    public Chat(String chat_id, String user_id, String service_provider_id) {
        this.chat_id = chat_id;
        this.user_id = user_id;
        this.service_provider_id = service_provider_id;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getService_provider_id() {
        return service_provider_id;
    }

    public void setService_provider_id(String service_provider_id) {
        this.service_provider_id = service_provider_id;
    }
}
