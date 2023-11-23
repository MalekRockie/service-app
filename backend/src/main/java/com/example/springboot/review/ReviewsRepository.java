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
        String sql = "SELECT * FROM public.\"reviews\" WHERE review_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, mapReviewsWithDB());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public int getReviewsAvg(String service_provider_id) {
        String sql = "SELECT AVG(stars) FROM public.\"reviews\" WHERE service_provider_id = ?";
        try {
            Double avg = jdbcTemplate.queryForObject(sql, new Object[]{service_provider_id}, Double.class);
            return avg == null ? 0 : avg.intValue();
        } catch (EmptyResultDataAccessException ex) {
            return 0;
        }
    }

    public void createReview(Reviews newReview) {
        String sql = "INSERT INTO public.\"reviews\" (review_id, stars, Description, User_id, service_provider_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                newReview.getReview_id(),
                newReview.getStars(),
                newReview.getDescription(),
                newReview.getUser_id(),
                newReview.getService_Provider_id());
    }


    private RowMapper<Reviews> mapReviewsWithDB() {
        return (resultSet, i) -> {
            return new Reviews(
                    resultSet.getString("Review_id"),
                    resultSet.getInt("stars"),
                    resultSet.getString("Description"),
                    resultSet.getString("User_id"),
                    resultSet.getString("service_provider_id"));
        };
    }
}
