package com.example.springboot.Chat;

import com.example.springboot.Orders.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ChatRepository {


    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public ChatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Chat> getChat(String id) {
        String sql = "SELECT * FROM public.\"chat\" WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, mapChatWithDB());
    }


    private RowMapper<Chat> mapChatWithDB() {
        return (resultSet, i) -> {
            return new Chat(
                    resultSet.getString("chat_id"),
                    resultSet.getString("user_id"),
                    resultSet.getString("service_provider_id"));
        };
    }
}
