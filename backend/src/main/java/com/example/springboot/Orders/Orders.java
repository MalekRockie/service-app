package com.example.springboot.Orders;

import java.util.Date;

public class Orders {
    private String order_id;
    private String User_id;
    private Date order_date;
    private String service_id;

    public Orders(String order_id, String user_id, Date order_date, String service_id) {
        this.order_id = order_id;
        this.User_id = user_id;
        this.order_date = order_date;
        this.service_id = service_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        service_id = service_id;
    }
}
