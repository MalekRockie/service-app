package com.example.springboot.ServiceProviders;

public class ServiceProvider {

    private String service_provider_id;
    private String username;
    private String first_name;
    private String last_name;
    private String password;
    private String email_address;
    private String phone_number;
    private String street_address1;
    private String street_address2;
    private String city;
    private int zip_code;

    public ServiceProvider(String service_provider_id, String username, String first_name, String last_name, String password, String email_address, String phone_number, String street_address1, String street_address2, String city, int zip_code) {
        this.service_provider_id = service_provider_id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.email_address = email_address;
        this.phone_number = phone_number;
        this.street_address1 = street_address1;
        this.street_address2 = street_address2;
        this.city = city;
        this.zip_code = zip_code;
    }

    public String getServiceProviderId() {
        return service_provider_id;
    }

    public void setServiceProviderId(String service_provider_id) {
        this.service_provider_id = service_provider_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return email_address;
    }

    public void setEmailAddress(String email_address) {
        this.email_address = email_address;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStreetAddress1() {
        return street_address1;
    }

    public void setStreetAddress1(String street_address1) {
        this.street_address1 = street_address1;
    }

    public String getStreetAddress2() {
        return street_address2;
    }

    public void setStreetAddress2(String street_address2) {
        this.street_address2 = street_address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zip_code;
    }

    public void setZipCode(int zip_code) {
        this.zip_code = zip_code;
    }
}
