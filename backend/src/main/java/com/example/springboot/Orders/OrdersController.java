package com.example.springboot.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("order/getAllOrders")
    public List<Orders> getAllOrder()
    {
        return ordersService.getAllOrder();
    }

    @GetMapping("order/getOrder/{id}")
    public Orders getOrder(@PathVariable String id)
    {
        return ordersService.getOrder(id);
    }
}
