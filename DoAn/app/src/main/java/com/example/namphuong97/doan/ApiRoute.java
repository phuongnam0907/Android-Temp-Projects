package com.example.namphuong97.doan;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by namphuong97 on 3/27/18.
 */

public interface ApiRoute {

    @GET("data")
    Call<ApiData> getData();

}
