package com.example.springboot.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewsRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public ReviewsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Reviews getReview(String id) {
        String sql = "SELECT * FROM public.\"reviews\" WHERE Review_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, mapReviewsWithDB());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    private RowMapper<Reviews> mapReviewsWithDB() {
        return (resultSet, i) -> {
            return new Reviews(
                    resultSet.getString("Review_id"),
                    resultSet.getInt("stars"),
                    resultSet.getString("Description"),
                    resultSet.getString("User_id"),
                    resultSet.getString("Service_Provider_id"));
        };
    }

}
