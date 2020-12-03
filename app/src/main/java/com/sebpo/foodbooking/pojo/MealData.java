package com.sebpo.foodbooking.pojo;

public class MealData {
    private String area;

    private String food;

    private String vendor;

    private String date;

    private String user;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

//    @Override
//    public String toString() {
//        return "ClassPojo [area = " + area + ", food = " + food + ", vendor = " + vendor + ", date = " + date + ", user = " + user + "]";
//    }
}