package com.example.springboot.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> getReviewsInfo(String service_provider_id) {
        String avgSql = "SELECT AVG(stars) FROM public.\"reviews\" WHERE service_provider_id = ?";
        String countSql = "SELECT COUNT(*) FROM public.\"reviews\" WHERE service_provider_id = ?";
        Map<String, Object> result = new HashMap<>();

        try {
            Double avg = jdbcTemplate.queryForObject(avgSql, new Object[]{service_provider_id}, Double.class);
            Integer count = jdbcTemplate.queryForObject(countSql, new Object[]{service_provider_id}, Integer.class);

            result.put("averageRating", avg == null ? 0 : avg);
            result.put("reviewCount", count == null ? 0 : count);
        } catch (EmptyResultDataAccessException ex) {
            result.put("averageRating", 0);
            result.put("reviewCount", 0);
        }

        return result;
    }
    public List<Reviews> getAllReviews(String service_provider_id) {
        String sql = "SELECT * FROM public.\"reviews\" WHERE service_provider_id = ?";
        return jdbcTemplate.query(sql, new Object[]{service_provider_id}, mapReviewsWithDB());
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
