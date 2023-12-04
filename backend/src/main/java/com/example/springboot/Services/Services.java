package com.example.springboot.Services;

public class Services {

    private String service_id;
    private String service_provider_id;
    private String service_category_id;
    private String service_price;
    private String service_name;

    public Services(String service_id, String service_provider_id, String service_category_id, String service_price, String service_name) {
        this.service_id = service_id;
        this.service_provider_id = service_provider_id;
        this.service_category_id = service_category_id;
        this.service_price = service_price;
        this.service_name = service_name;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getService_provider_id() {
        return service_provider_id;
    }

    public void setService_provider_id(String service_provider_id) {
        this.service_provider_id = service_provider_id;
    }

    public String getService_category_id() {
        return service_category_id;
    }

    public void setService_category_id(String service_category_id) {
        this.service_category_id = service_category_id;
    }

    public String getService_price() {
        return service_price;
    }

    public void setService_price(String service_price) {
        this.service_price = service_price;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }
}
