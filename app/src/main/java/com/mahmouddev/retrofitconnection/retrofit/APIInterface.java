package com.mahmouddev.retrofitconnection.retrofit;

import com.mahmouddev.retrofitconnection.models.TodoResponse;
import com.mahmouddev.retrofitconnection.models.UsersResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {



    @GET("users")
    Call<ArrayList<UsersResponse>> getUsers();

    @GET("users")
    Call<ArrayList<UsersResponse>> getUsersById(@Query("userId") int id);

    @GET("todos")
    Call<ArrayList<TodoResponse>> getTodoByUserId(@Query("userId") int id, @Query("completed") boolean isCompleted);

    @GET("todos")
    Call<ArrayList<TodoResponse>> getTodoByUserId(@Query("userId") int id);
}
