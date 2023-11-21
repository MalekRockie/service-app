package com.example.springboot.Message;

import com.example.springboot.ServiceProviders.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
