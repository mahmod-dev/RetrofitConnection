package com.mahmouddev.retrofitconnection;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class APIClient {


    private static Retrofit retrofit;
    private static final String BASE_URL = "http://tatbeqakum.salehly.com/public/api/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}