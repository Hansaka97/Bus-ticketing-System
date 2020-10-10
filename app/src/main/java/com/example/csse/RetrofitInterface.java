package com.example.csse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResults> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executesignup (@Body HashMap<String, String> map);

    @POST("/accountsubmit")
    Call<Void> executeaccountsubmit (@Body HashMap<String, String> map);
}
