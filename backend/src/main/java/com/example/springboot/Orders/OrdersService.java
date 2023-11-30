package com.example.springboot.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;
    public List<Orders> getAllOrder()
    {
        return ordersRepository.getAllOrders();
    }

    public Orders getOrder(String id) {
        return ordersRepository.getOrder(id);
    }

    public void requestService(Orders newOrder) {
        ordersRepository.requestService(newOrder);
    }

    public List<Orders> getOrderByService(String id) {
        return ordersRepository.getOrderByService(id);
    }

    public void ChangeStatus(String id, String status) {
        ordersRepository.changeStatus(id, status);
    }
}
