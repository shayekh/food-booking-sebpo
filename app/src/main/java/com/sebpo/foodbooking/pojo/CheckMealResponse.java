package com.sebpo.foodbooking.pojo;

import com.google.gson.annotations.SerializedName;

public class CheckMealResponse {
    private String message;

    private String status;

    @SerializedName("data")
    private MealData data;

    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MealData getData() {
        return data;
    }

    public void setData(MealData data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", status = " + status + ", data = " + data + ", code = " + code + "]";
    }
}