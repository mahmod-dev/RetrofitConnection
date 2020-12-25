package com.mahmouddev.retrofitconnection;

import com.mahmouddev.retrofitconnection.models.LoginPost;
import com.mahmouddev.retrofitconnection.models.LoginResponse;
import com.mahmouddev.retrofitconnection.models.SignUpPost;
import com.mahmouddev.retrofitconnection.models.SignUpResponse;
import com.mahmouddev.retrofitconnection.models.StoreResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpTest {

    CountDownLatch latch = new CountDownLatch(1);
    SignUpResponse signUpResponse;

    @Before
    public void beforeTest() {
        signUpResponse = new SignUpResponse();
    }

    // test successful
    @Test
    public void login_Successful() throws Exception {
        SignUpPost post = new SignUpPost();
        post.setEmail("wwwzz@gmail.com");
        post.setMobile("0668748552");
        post.setPassword("123456");
        post.setDevice_type();
        post.setFcm_token("abccde");


        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<SignUpResponse> call = service.signUp(post);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                signUpResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                latch.countDown();

            }

        });

        latch.await();
        Assert.assertNotNull(signUpResponse);
        Assert.assertEquals("wwwzz@gmail.com", signUpResponse.getUser().getEmail());
        Assert.assertEquals("0668748552", signUpResponse.getUser().getMobile());
        Assert.assertEquals(200, signUpResponse.getCode());
    }


    // test failed duplicate email
    @Test
    public void login_failed_sameEmail() throws Exception {
        SignUpPost post = new SignUpPost();
        post.setEmail("aaaa@gmail.com");
        post.setMobile("0558748511");
        post.setPassword("000000");
        post.setFcm_token("abccdf");
        post.setDevice_type();


        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<SignUpResponse> call = service.signUp(post);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                signUpResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                latch.countDown();

            }

        });

        latch.await();
        Assert.assertNull(signUpResponse.getUser());
        Assert.assertEquals(210, signUpResponse.getCode());
        Assert.assertNotNull(signUpResponse.getMessage());
    }

    // test failed duplicate mobile
    @Test
    public void login_failed_sameMobile() throws Exception {
        SignUpPost post = new SignUpPost();
        post.setEmail("rerert@gmail.com");
        post.setMobile("0597796100");
        post.setPassword("000000");
        post.setFcm_token("abccdg");
        post.setDevice_type();


        APIInterface service = APIClient.getRetrofitInstance().create(APIInterface.class);
        Call<SignUpResponse> call = service.signUp(post);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                signUpResponse = response.body();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                latch.countDown();

            }

        });

        latch.await();
        Assert.assertNull(signUpResponse.getUser());
        Assert.assertEquals(210, signUpResponse.getCode());
        Assert.assertNotNull(signUpResponse.getMessage());
    }

}
