package com.example.csse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csse.model.BusAndRouteResults;
import com.example.csse.model.PassengerAccountModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PassengerConfirmation extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.7:3000";

    private static String bus_id = "";
    private static String user_id = "";
    private static String email = "";


    private String session_id = "";
    private String reg_no = "";
    private String type = "";
    private String no_of_seats = "";
    private String route_id = "";

    private String start_location = "";
    private String destination = "";
    private String route_no = "";
    private String fare = "";
    private String start_time = "";
    private int totalBusfare = 0;
    private int userEnterCount = 0;

    //User Account Details
    private String user_name = "";
    private String nic = "";
    private String phoneNo = "";
    private String amount = "";


    TextView routeTypeTxt;
    TextView routeText;
    TextView registerNoText;
    TextView timeText;
    TextView seatcountText;
    TextView seatcountTextFinal;
    TextView busfareText;
    TextView TotalbusfareText;
    TextView errorText;

    Button buttonMines;
    Button buttonPluse;
    Button buttonPayAndStart;
    Button buttonPayCash;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_confirmation);

        routeTypeTxt = findViewById(R.id.routeTypeTxt);
        routeText = findViewById(R.id.routeText);
        registerNoText = findViewById(R.id.registerNoText);
        timeText = findViewById(R.id.timeText);
        seatcountText = findViewById(R.id.seatcountText);
        seatcountTextFinal = findViewById(R.id.seatcountTextFinal);
        busfareText = findViewById(R.id.busfareText);
        TotalbusfareText = findViewById(R.id.TotalbusfareText);
        errorText = findViewById(R.id.errorText);
        buttonMines = findViewById(R.id.buttonMines);
        buttonPluse = findViewById(R.id.buttonPluse);
        buttonPayAndStart = findViewById(R.id.buttonPayAndStart);
        buttonPayCash = findViewById(R.id.buttonPayCash);


        errorText.setVisibility(View.GONE);
        buttonPayCash.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            bus_id = extras.getString("bus_id");
            email = extras.getString("email");
            user_id = extras.getString("_id");
        }

        System.out.println("User Email : " + email);
        System.out.println("User User ID : " + user_id);
        System.out.println("Bus ID : " + bus_id);


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        HashMap<String ,String> map = new HashMap<>();
        map.put("bus_id", bus_id);
        Call<BusAndRouteResults> call = retrofitInterface.executeBusAndRouteDetails(map);

        call.enqueue(new Callback<BusAndRouteResults>() {
            @Override
            public void onResponse(Call<BusAndRouteResults> call, Response<BusAndRouteResults> response) {

                if(response.code() == 200){
                    reg_no = response.body().getReg_no();
                    type = response.body().getType();
                    no_of_seats = response.body().getNo_of_seats();
                    route_id = response.body().getRoute_id();
                    start_location = response.body().getStart_location();
                    destination = response.body().getDestination();
                    route_no = response.body().getRoute_no();
                    fare = response.body().getFare();
                    start_time = response.body().getStart_time();
                    session_id = response.body().getSession_id();

                    routeTypeTxt.setText(type);
                    routeText.setText((start_location + " - " + destination));
                    registerNoText.setText(reg_no);
                    timeText.setText(start_time);
                    busfareText.setText(fare);



                    //Get User Account Details
                    HashMap<String ,String> map2 = new HashMap<>();
                    map2.put("email", email);
                    Call<PassengerAccountModel> call2 = retrofitInterface.executegetUSerDetails(map2);

                    call2.enqueue(new Callback<PassengerAccountModel>() {
                        @Override
                        public void onResponse(Call<PassengerAccountModel> call2, Response<PassengerAccountModel> response) {

                            if(response.code() == 200){

                                user_name = response.body().getName();
                                nic = response.body().getNic();
                                phoneNo = response.body().getPhoneNo();
                                amount = response.body().getAmount();

                            } else if(response.code() == 404){


                            }



                        }

                        @Override
                        public void onFailure(Call<PassengerAccountModel> call2, Throwable t) {

                            Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


                } else if(response.code() == 404){

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("_id", user_id);
                    i.putExtra("email", email);
                    i.putExtra("bus_id", bus_id);
                    startActivity(i);
                    Toast.makeText( getApplicationContext(), "Ops!!! Bus Session Not Start Yet.", Toast.LENGTH_LONG).show();
                }



            }

            @Override
            public void onFailure(Call<BusAndRouteResults> call, Throwable t) {

                Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });




        buttonPluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userEnterCount < 5){
                    userEnterCount += 1;
                    seatcountText.setText(String.valueOf(userEnterCount));
                    CalculateTotalBusFare(userEnterCount);
                } else {
                    Toast.makeText( getApplicationContext(), "Reservation Maximum Seat Count Reach. ", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonMines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userEnterCount > 1){
                    userEnterCount -= 1;
                    seatcountText.setText(String.valueOf(userEnterCount));
                    CalculateTotalBusFare(userEnterCount);
                } else {

                }
            }
        });

        buttonPayAndStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int userBankAmount = 0;
                userBankAmount = Integer.parseInt(amount);

                int remainBalance = userBankAmount - totalBusfare;

                if(userEnterCount > 0){

                    if(userBankAmount > totalBusfare){

                        retrofitInterface = retrofit.create(RetrofitInterface.class);
                        HashMap<String ,String> mapUS = new HashMap<>();
                        mapUS.put("bus_id", bus_id);
                        mapUS.put("session_id", session_id);
                        mapUS.put("user_id", user_id);
                        mapUS.put("email", email);
                        mapUS.put("name", user_name);
                        mapUS.put("phoneNo", phoneNo);
                        mapUS.put("noOfSeats", String.valueOf(userEnterCount));
                        mapUS.put("totalFare", String.valueOf(totalBusfare));
                        mapUS.put("paymentMethod", "ONLINE");
                        mapUS.put("isPay", "YES");
                        mapUS.put("balance", String.valueOf(remainBalance));

                        Call<Void> call2 = retrofitInterface.executeAddUserToSession(mapUS);

                        call2.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call2, Response<Void> response) {

                                if(response.code() == 200){


                                    Intent i = new Intent(getApplicationContext(), StartTrip.class);
                                    i.putExtra("msg", "Payment Success!!!");
                                    startActivity(i);


                                } else if(response.code() == 404){


                                }



                            }

                            @Override
                            public void onFailure(Call<Void> call2, Throwable t) {

                                Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    } else {
                        Toast.makeText( getApplicationContext(), "Your Account Balance Not enough to pay.Please Pay Cash.", Toast.LENGTH_LONG).show();
                        buttonPayAndStart.setVisibility(View.GONE);
                        errorText.setVisibility(View.VISIBLE);
                        buttonPayCash.setVisibility(View.VISIBLE);

                    }
                } else {
                    Toast.makeText( getApplicationContext(), "Please Enter Seat Count.", Toast.LENGTH_LONG).show();
                }



            }
        });

        buttonPayCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                retrofitInterface = retrofit.create(RetrofitInterface.class);
                HashMap<String ,String> mapUS = new HashMap<>();
                mapUS.put("bus_id", bus_id);
                mapUS.put("session_id", session_id);
                mapUS.put("user_id", user_id);
                mapUS.put("email", email);
                mapUS.put("name", user_name);
                mapUS.put("phoneNo", phoneNo);
                mapUS.put("noOfSeats", String.valueOf(userEnterCount));
                mapUS.put("totalFare", String.valueOf(totalBusfare));
                mapUS.put("paymentMethod", "CASH");
                mapUS.put("isPay", "NO");
                mapUS.put("balance", amount);

                Call<Void> call2 = retrofitInterface.executeAddUserToSession(mapUS);

                call2.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call2, Response<Void> response) {

                        if(response.code() == 200){


                            Intent i = new Intent(getApplicationContext(), StartTrip.class);
                            i.putExtra("msg", "You Have To Pay CASH.");
                            startActivity(i);


                        } else if(response.code() == 404){


                        }



                    }

                    @Override
                    public void onFailure(Call<Void> call2, Throwable t) {

                        Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


            }
        });

    }

    public void CalculateTotalBusFare(int noOfSeet){

        totalBusfare = noOfSeet * Integer.parseInt(fare);
        seatcountTextFinal.setText(String.valueOf(noOfSeet));
        TotalbusfareText.setText(String.valueOf(totalBusfare));
    }
}