package com.example.springboot.ServiceProviders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceProviderRepository {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ServiceProviderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ServiceProvider getServiceProvider(String id) {
        String sql = "SELECT * FROM public.\"Service Provider\" WHERE service_provider_id = ?";
        try {
            ServiceProvider serviceProvider = jdbcTemplate.queryForObject(sql, new Object[]{id}, mapServiceProviderWithDB());
            return serviceProvider;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private RowMapper<ServiceProvider> mapServiceProviderWithDB() {
        return (resultSet, i) -> {
            return new ServiceProvider(
                    resultSet.getString("service_provider_id"),
                    resultSet.getString("username"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("password"),
                    resultSet.getString("email_address"),
                    resultSet.getInt("phone_number"),
                    resultSet.getString("street_address_1"),
                    resultSet.getString("street_address_2"),
                    resultSet.getString("city"),
                    resultSet.getInt("zip_code"));
        };
    }
}
