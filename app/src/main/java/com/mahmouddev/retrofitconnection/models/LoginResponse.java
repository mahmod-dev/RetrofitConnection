package com.mahmouddev.retrofitconnection.models;

public class LoginResponse {

    int code;
    boolean status;
    User user;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "code=" + code +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
