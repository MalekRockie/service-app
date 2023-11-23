package com.example.springboot.review;

public class Reviews {

    private String Review_id;
    private int stars;
    private String Description;
    private String User_id;
    private String Service_Provider_id;

    public Reviews(String review_id, int stars, String description, String user_id, String service_Provider_id) {
        this.Review_id = review_id;
        this.stars = stars;
        this.Description = description;
        this.User_id = user_id;
        this.Service_Provider_id = service_Provider_id;
    }

    public String getReview_id() {
        return Review_id;
    }

    public void setReview_id(String review_id) {
        Review_id = review_id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUser_id() {
        return User_id;
    }

    public void setUser_id(String user_id) {
        User_id = user_id;
    }

    public String getService_Provider_id() {
        return Service_Provider_id;
    }

    public void setService_Provider_id(String service_Provider_id) {
        Service_Provider_id = service_Provider_id;
    }
}
