package com.example.springboot.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ServicesRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ServicesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Services getServiceById(String id) {
            String sql = "SELECT * FROM public.\"Services\" WHERE service_id = ?";
            try {
                Services services = jdbcTemplate.queryForObject(sql, new Object[]{id}, mapServiceWithDB());
                return services;
            } catch (EmptyResultDataAccessException ex) {
                return null;
            }
    }

    public Services getServiceByProviderId(String id) {
        String sql = "SELECT * FROM public.\"Services\" WHERE service_provider_id = ?";
        try {
            Services services = jdbcTemplate.queryForObject(sql, new Object[]{id}, mapServiceWithDB());
            return services;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private RowMapper<Services> mapServiceWithDB() {
        return (resultSet, i) -> {
            return new Services(
                    resultSet.getString("service_id"),
                    resultSet.getString("service_provider_id"),
                    resultSet.getString("service_category_id"),
                    resultSet.getString("service_price"),
                    resultSet.getString("service_name"));
        };
    }
}
