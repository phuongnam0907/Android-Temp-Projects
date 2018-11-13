package com.example.namphuong97.doan;

import android.telecom.Call;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by namphuong97 on 3/27/18.
 */

public class ApiController implements Callback<ApiData> {


    public static final String BASE_URL =
            "http://test.apitiny.com/api/bkit/";
    public ApiListenser listenser;

    public interface ApiListenser {
        void onGetData(ApiData data);
        void onFail(Throwable t);
    }

    public void addListener(ApiListenser listenser) {
        this.listenser = listenser;
    }

    @Override
    public void onResponse(retrofit2.Call<ApiData> call, Response<ApiData> response) {
        if (response.isSuccessful()) {
            ApiData data = response.body();
            listenser.onGetData(data);
        } else {
            ApiData data = new ApiData();
            try {
                data.errorMessage = response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            listenser.onGetData(data);
        }
    }

    @Override
    public void onFailure(retrofit2.Call<ApiData> call, Throwable t) {
        listenser.onFail(t);
        t.printStackTrace();
    }

    public ApiController getData() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiRoute route = retrofit.create(ApiRoute.class);
        retrofit2.Call<ApiData> call = route.getData();
        call.enqueue(this);
        return this;
    }

}
