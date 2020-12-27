
package com.mahmouddev.retrofitconnection.models;

import com.google.gson.annotations.SerializedName;

public class Geo {

    @SerializedName("lat")
    private String mLat;
    @SerializedName("lng")
    private String mLng;

    public String getLat() {
        return mLat;
    }

    public void setLat(String lat) {
        mLat = lat;
    }

    public String getLng() {
        return mLng;
    }

    public void setLng(String lng) {
        mLng = lng;
    }

}
