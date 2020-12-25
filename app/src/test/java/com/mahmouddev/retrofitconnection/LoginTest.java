package com.mahmouddev.retrofitconnection;

import com.mahmouddev.retrofitconnection.models.LoginPost;
import com.mahmouddev.retrofitconnection.models.LoginResponse;
import com.mahmouddev.retrofitconnection.models.StoreResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginTest {

    CountDownLatch latch = new CountDownLatch(1);
    LoginResponse loginResponse;
    StoreResponse storeResponse;

    @Before
    public void beforeTest() {
        loginResponse = new LoginResponse();
        storeResponse = new StoreResponse();
    }

    // test successful
    @Test
    public void login_Successful() throws Exception {
        LoginPost post = new LoginPost();
        post.setEmail("aaaa@gmail.com");
        post.setPassword("123456");
        post.setDevice_type("android");
        post.setFcm_token("sssasas");


        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<LoginResponse> call = service.login(post);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }

        });

        latch.await();
        Assert.assertNotNull(loginResponse);
        Assert.assertEquals("aaaa@gmail.com", loginResponse.getUser().getEmail());
        Assert.assertEquals(200, loginResponse.getCode());
    }


    //test Failed (wrong email)
    @Test
    public void login_Failed_wrongEmail() throws Exception {
        LoginPost post = new LoginPost();
        post.setEmail("aabb@gmail.com");
        post.setPassword("123456");
        post.setDevice_type("android");
        post.setFcm_token("sssasas");


        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<LoginResponse> call = service.login(post);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }

        });

        latch.await();
        Assert.assertNull("Opss! object is null", loginResponse.getUser());
        Assert.assertEquals(210, loginResponse.getCode());
    }


    //test Failed (null email and password)
    @Test
    public void login_Failed_nullEmailPassword() throws Exception {
        LoginPost post = new LoginPost();
        post.setEmail(null);
        post.setPassword(null);
        post.setDevice_type("android");
        post.setFcm_token("sssasas");


        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<LoginResponse> call = service.login(post);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
                //  Log.e("TAG", "onFailure: "+t.getMessage() );
            }

        });

        latch.await();
        Assert.assertNull("Opss! object is null", loginResponse.getUser());
        Assert.assertEquals(210, loginResponse.getCode());
        Assert.assertFalse("status false", loginResponse.isStatus());
    }


    @Test
    public void getStores_Successful() throws Exception {


        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<StoreResponse> call = service.getActivities();
        call.enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                storeResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                latch.countDown();
            }

        });

        latch.await();
        Assert.assertNotNull(storeResponse);
    }
}
