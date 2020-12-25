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

public class StoreTest {

    CountDownLatch latch = new CountDownLatch(1);
    StoreResponse storeResponse;

    @Before
    public void beforeTest() {
        storeResponse = new StoreResponse();
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
