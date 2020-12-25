package com.mahmouddev.retrofitconnection.models;

public class StoreResponse {
    String created_at;
    String image;
    String nameStore;
    String status;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StoreResponse{" +
                "created_at='" + created_at + '\'' +
                ", image='" + image + '\'' +
                ", name='" + nameStore + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
