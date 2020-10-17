package com.example.csse;

import com.example.csse.model.BusAndRouteResults;
import com.example.csse.model.PassengerAccountModel;
import com.example.csse.model.PassengerLayoutModel;
import com.example.csse.model.SessionModelResult;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<LoginResults> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executesignup (@Body HashMap<String, String> map);

    @POST("/accountsubmit")
    Call<Void> executeaccountsubmit (@Body HashMap<String, String> map);

    @POST("/getbusandroute")
    Call<BusAndRouteResults> executeBusAndRoute(@Body HashMap<String, String> map);

    @POST("/startsession")
    Call<SessionModelResult> executestartsession (@Body HashMap<String, String> map);

    @POST("/getsession")
    Call<SessionModelResult> executeGetSession (@Body HashMap<String, String> map);

    @POST("/getbusandrouteDetails")
    Call<BusAndRouteResults> executeBusAndRouteDetails(@Body HashMap<String, String> map);

    @POST("/getUserDetails")
    Call<PassengerAccountModel> executegetUSerDetails(@Body HashMap<String, String> map);

    @POST("/addUserToSession")
    Call<Void> executeAddUserToSession(@Body HashMap<String, String> map);

    @POST("/endSession")
    Call<Void> executeEndSession(@Body HashMap<String, String> map);

    @POST("/getAllPassengers")
    Call<List<PassengerLayoutModel>> executegetpassengers(@Body HashMap<String, String> map);

}
