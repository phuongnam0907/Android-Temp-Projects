package com.example.namphuong97.doan;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by namphuong97 on 3/27/18.
 */

public class ApiData {

    @SerializedName("data")
    ArrayList<Device> data;

    @SerializedName("errorMessage")
    String errorMessage;

}
