package com.example.springboot.Orders;

import com.example.springboot.Message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrdersRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public OrdersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Orders> getAllOrders() {
        String sql = "SELECT * FROM public.\"Orders\"";
        return jdbcTemplate.query(sql, mapOrdersWithDB());
    }

    public Orders getOrder(String order_id) {
        String sql = "SELECT * FROM public.\"Orders\" WHERE order_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{order_id}, mapOrdersWithDB());
    }

    private RowMapper<Orders> mapOrdersWithDB() {
        return (resultSet, i) -> {
            return new Orders(
                    resultSet.getString("order_id"),
                    resultSet.getString("User_id"),
                    resultSet.getDate("order_date"),
                    resultSet.getString("Service_id"));
        };
    }
}
