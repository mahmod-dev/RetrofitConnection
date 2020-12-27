package com.mahmouddev.retrofitconnection;

import com.mahmouddev.retrofitconnection.models.TodoResponse;
import com.mahmouddev.retrofitconnection.models.UsersResponse;
import com.mahmouddev.retrofitconnection.retrofit.APIClient;
import com.mahmouddev.retrofitconnection.retrofit.APIInterface;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataTest {

    CountDownLatch latch = new CountDownLatch(1);
    ArrayList<UsersResponse> usersResponse;
    ArrayList<TodoResponse> todoResponses;
    ArrayList<TodoResponse> arr = new ArrayList<>();

    @Before
    public void beforeTest() {
        usersResponse = new ArrayList<UsersResponse>();
        todoResponses = new ArrayList<TodoResponse>();
    }

    // test successful
    @Test
    public void getUsers_Successful() throws Exception {

        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<ArrayList<UsersResponse>> call = service.getUsers();
        call.enqueue(new Callback<ArrayList<UsersResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<UsersResponse>> call, Response<ArrayList<UsersResponse>> response) {
                usersResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<ArrayList<UsersResponse>> call, Throwable t) {

            }

        });

        latch.await();
        Assert.assertNotNull(usersResponse);
        Assert.assertEquals("Sincere@april.biz", usersResponse.get(0).getEmail());
        Assert.assertEquals(1, usersResponse.get(0).getId());
    }

    @Test
    public void getUserById_success() throws Exception {
        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<ArrayList<UsersResponse>> call = service.getUsersById(100);
        call.enqueue(new Callback<ArrayList<UsersResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<UsersResponse>> call, Response<ArrayList<UsersResponse>> response) {
                usersResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<ArrayList<UsersResponse>> call, Throwable t) {

            }

        });

        latch.await();
        Assert.assertEquals(10, usersResponse.size());
    }
////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getTodoByUserId_success() throws Exception {
        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<ArrayList<TodoResponse>> call = service.getTodoByUserId(2);
        call.enqueue(new Callback<ArrayList<TodoResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoResponse>> call, Response<ArrayList<TodoResponse>> response) {
                todoResponses = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<ArrayList<TodoResponse>> call, Throwable t) {

            }

        });

        latch.await();
        Assert.assertEquals(false, todoResponses.get(0).getCompleted());
        Assert.assertEquals(20, todoResponses.size());
    }

    @Test
    public void getTodoByUserId_fail() throws Exception {
        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<ArrayList<TodoResponse>> call = service.getTodoByUserId(100);
        call.enqueue(new Callback<ArrayList<TodoResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoResponse>> call, Response<ArrayList<TodoResponse>> response) {
                todoResponses = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<ArrayList<TodoResponse>> call, Throwable t) {

            }

        });

        latch.await();
        Assert.assertEquals(new ArrayList<>(), todoResponses);
        Assert.assertEquals(0, todoResponses.size());
        Assert.assertArrayEquals(new ArrayList[]{arr}, new ArrayList[]{todoResponses});
        Assert.assertThrows(IndexOutOfBoundsException.class,()->todoResponses.get(0));

    }

////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void getTodoByUserIdAndCompletedTrue_success() throws Exception {
        Random random = new Random();

      int ran1To10 =   random.nextInt(10 - 1 + 1) + 1;

        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<ArrayList<TodoResponse>> call = service.getTodoByUserId(10,true);
        call.enqueue(new Callback<ArrayList<TodoResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoResponse>> call, Response<ArrayList<TodoResponse>> response) {
                todoResponses = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<ArrayList<TodoResponse>> call, Throwable t) {

            }

        });

        latch.await();
        Assert.assertNotNull("not null object", todoResponses);
        Assert.assertEquals(12, todoResponses.size());
        Assert.assertEquals("index is: "+ran1To10,true, todoResponses.get(ran1To10).getCompleted());
    }


    @Test
    public void getTodoByUserIdAndCompletedFalse_success() throws Exception {
        Random random = new Random();

        int ran1To10 =   random.nextInt(10 - 1 + 1) + 1;

        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<ArrayList<TodoResponse>> call = service.getTodoByUserId(2,false);
        call.enqueue(new Callback<ArrayList<TodoResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoResponse>> call, Response<ArrayList<TodoResponse>> response) {
                todoResponses = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<ArrayList<TodoResponse>> call, Throwable t) {

            }

        });

        latch.await();
        Assert.assertNotNull("not null object", todoResponses);
        Assert.assertEquals(12, todoResponses.size());
        Assert.assertEquals("index is: "+ran1To10,false, todoResponses.get(ran1To10).getCompleted());
        Assert.assertEquals("laborum aut in quam", todoResponses.get(4).getTitle());
    }


    @Test
    public void getTodoByUserIdAndCompletedFalse_fail() throws Exception {
        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<ArrayList<TodoResponse>> call = service.getTodoByUserId(5000,false);
        call.enqueue(new Callback<ArrayList<TodoResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoResponse>> call, Response<ArrayList<TodoResponse>> response) {
                todoResponses = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<ArrayList<TodoResponse>> call, Throwable t) {

            }

        });

        latch.await();
        Assert.assertEquals("size 0, there isnt userId: 5000",0, todoResponses.size());
        Assert.assertArrayEquals(new ArrayList[]{arr}, new ArrayList[]{todoResponses});
        Assert.assertThrows(IndexOutOfBoundsException.class,()->todoResponses.get(0));

    }

}
