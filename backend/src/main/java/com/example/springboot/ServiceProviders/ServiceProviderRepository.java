package com.example.springboot.ServiceProviders;

import com.example.springboot.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<ServiceProvider> getProviders()
    {
        String sql = "SELECT * FROM  public.\"Service Provider\"";
        return jdbcTemplate.query(sql, mapServiceProviderWithDB());
    }

    public void SignUp(ServiceProvider newUser) {
        String sql = "INSERT INTO public.\"Service Provider\" (service_provider_id, username, first_name, last_name, password, email_address, phone_number, street_address_1, street_address_2, city, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, newUser.getServiceProviderId(), newUser.getUsername(), newUser.getFirstName(), newUser.getLastName(), newUser.getPassword(), // Consider hashing the password
                newUser.getEmailAddress(), newUser.getPhoneNumber(), newUser.getStreetAddress1(), newUser.getStreetAddress2(), newUser.getCity(), newUser.getZipCode());
    }
    public ServiceProvider getByUsername(String username) {
        String sql = "SELECT * FROM public.\"Service Provider\" WHERE email_address = ?";
        try {
            ServiceProvider serviceProvider = jdbcTemplate.queryForObject(sql, new Object[]{username}, mapServiceProviderWithDB());
            return serviceProvider;
        } catch (EmptyResultDataAccessException ex) {
            // Handle the case when no user is found
            return null; // Or throw a custom exception
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
                    resultSet.getString("phone_number"),
                    resultSet.getString("street_address_1"),
                    resultSet.getString("street_address_2"),
                    resultSet.getString("city"),
                    resultSet.getInt("zip_code"));
        };
    }
}
