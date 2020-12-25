package com.mahmouddev.retrofitconnection;

import com.mahmouddev.retrofitconnection.models.LoginPost;
import com.mahmouddev.retrofitconnection.models.LoginResponse;
import com.mahmouddev.retrofitconnection.models.SignUpPost;
import com.mahmouddev.retrofitconnection.models.SignUpResponse;
import com.mahmouddev.retrofitconnection.models.StoreResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @POST("login")
    Call<LoginResponse> login(@Body LoginPost post);

    @POST("signUp")
    Call<SignUpResponse> signUp(@Body SignUpPost activityId);


    @GET("getActivities")
    Call<StoreResponse> getActivities();
}
