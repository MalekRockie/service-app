package com.example.springboot.Message;

import com.example.springboot.ServiceProviders.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
@Repository
public class MessageRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public MessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Message> getMessages(String chat_id) {
        String sql = "SELECT * FROM public.\"messages\" WHERE chat_id = ?";
        return jdbcTemplate.query(sql, new Object[]{chat_id}, mapMessageWithDB());
    }
    public void sendMessage(Message msg) {
        String sql = "INSERT INTO public.\"messages\" (message_id, chat_id, send_time, sender_id, \"messageContent\") VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, msg.getMessage_id(), msg.getChat_id(), new Timestamp(msg.getSend_time().getTime()), msg.getSender_id(), msg.getMessageContent());
    }

    private RowMapper<Message> mapMessageWithDB() {
        return (resultSet, i) -> {
            return new Message(
                    resultSet.getString("message_id"),
                    resultSet.getString("chat_id"),
                    resultSet.getDate("send_time"),
                    resultSet.getString("sender_id"),
                    resultSet.getString("messageContent"));
        };
    }
}
