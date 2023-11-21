package com.example.springboot.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {


    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUser(String id) {
        String sql = "SELECT * FROM public.\"Users\" WHERE user_id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, mapUserWithDB());
            return user;
        } catch (EmptyResultDataAccessException ex) {
            // Handle the case when no user is found
            return null; // Or throw a custom exception
        }
    }
    public User getByUsername(String username) {
        String sql = "SELECT * FROM user_account WHERE username = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{username}, mapUserWithDB());
            return user;
        } catch (EmptyResultDataAccessException ex) {
            // Handle the case when no user is found
            return null; // Or throw a custom exception
        }
    }

    private RowMapper<User> mapUserWithDB() {
        return (resultSet, i) -> {
            return new User(
                    resultSet.getString("user_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("password"),
                    resultSet.getInt("phone_number"),
                    resultSet.getString("email_address"),
                    resultSet.getString("street_address_1"),
                    resultSet.getString("street_address_2"),
                    resultSet.getString("city"),
                    resultSet.getInt("zip_code"));
        };
    }
}