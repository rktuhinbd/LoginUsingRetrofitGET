package com.multibrandinfotech.retrofitget;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Md. Rejaul Karim on 1/6/2019.
 * Copyright (c) 2019 MULTIBRAND INFOTECH LTD
 */
public interface Api {
    public String base_url = "http://192.168.1.83:8086/android_test/";

    @GET("login.php")
    Call<List<Login>> loginData();
}
