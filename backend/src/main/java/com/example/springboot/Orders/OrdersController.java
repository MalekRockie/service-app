package com.example.springboot.Orders;

import com.example.springboot.ServiceProviders.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/order/getOrdersForService/{id}")
    public List<Orders> getOrderByService(@PathVariable String id)
    {
        return ordersService.getOrderByService(id);
    }

    @PostMapping("order/RequestService")
    public ResponseEntity<?> requestService(@RequestBody Orders newOrder)
    {
        ordersService.requestService(newOrder);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("order/OrderStatus")
    public ResponseEntity<?> ChangeStatus(@PathVariable String id, @PathVariable String status)
    {
        ordersService.ChangeStatus(id, status);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
