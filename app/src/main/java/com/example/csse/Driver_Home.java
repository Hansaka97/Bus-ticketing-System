package com.example.csse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csse.model.BusAndRouteResults;
import com.example.csse.model.SessionModelResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Driver_Home extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.7:3000";

    private String session_id = "";
    private String bus_id = "";
    private String reg_no = "";
    private String type = "";
    private String no_of_seats = "";
    private String route_id = "";

    private String start_location = "";
    private String destination = "";
    private String route_no = "";

    private String driveSelectStartLocation = "";

    RadioGroup locationRadioGroup;
    RadioGroup timeRadioGroup;
    RadioButton locationRadioButton;
    RadioButton timeRadioButton;

    //Location Radio button get for set Text
    RadioButton locationRadioButton01;
    RadioButton locationRadioButton02;

    //time AM PM radio Button
    RadioButton amRadioButton;
    RadioButton pmRadioButton;

    TextView busNoTextView;
    TextView paragraphTextView;
    EditText timeHourEditText;
    EditText timeMinutesEditText;

    Button startSessionButton;
    Button endSessionButton;
    Button noOfPassengerButton;
    ImageButton ButtonLogout;

    boolean isSessionStart = false;
    String _id = "";

    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__home);

        locationRadioGroup = findViewById(R.id.locationGroup);
        timeRadioGroup = findViewById(R.id.timeGroup);

        //Location Radio button get for set Text
        locationRadioButton01 = findViewById(R.id.radioButton01);
        locationRadioButton02 = findViewById(R.id.radioButton02);

        amRadioButton = findViewById(R.id.radioButtonAm);
        pmRadioButton = findViewById(R.id.radioButton2PM);

        busNoTextView = findViewById(R.id.busNoTxt);
        paragraphTextView = findViewById(R.id.textView11);
        timeHourEditText = findViewById(R.id.editTextNumber);
        timeMinutesEditText = findViewById(R.id.editTextNumber2);

        //Buttons
        startSessionButton = findViewById(R.id.buttonSessionStart);
        endSessionButton = findViewById(R.id.buttonSessionEnd);
        noOfPassengerButton = findViewById(R.id.buttonNoOfPassenger);
        ButtonLogout = findViewById(R.id.ButtonLogout);


        //Set Button Visibility.
        setVisibility();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email = extras.getString("email");
            _id = extras.getString("_id");
        }

        System.out.println(email);
        System.out.println(_id);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String ,String> map = new HashMap<>();
        map.put("driver_id", _id);
        Call<BusAndRouteResults> call = retrofitInterface.executeBusAndRoute(map);

        call.enqueue(new Callback<BusAndRouteResults>() {
            @Override
            public void onResponse(Call<BusAndRouteResults> call, Response<BusAndRouteResults> response) {

                if(response.code() == 200){
                    bus_id  = response.body().getBus_id();
                    reg_no = response.body().getReg_no();
                    type  = response.body().getType();
                    no_of_seats = response.body().getNo_of_seats();
                    route_id  = response.body().getRoute_id();

                    start_location  = response.body().getStart_location();
                    destination = response.body().getDestination();
                    route_no  = response.body().getRoute_id();

                    //set Text Values
                    busNoTextView.setText(reg_no);
                    locationRadioButton01.setText(start_location);
                    locationRadioButton02.setText(destination);


                    //Check Session is Start Before
                    HashMap<String ,String> sessionMap = new HashMap<>();
                    sessionMap.put("bus_id", bus_id);
                    sessionMap.put("isStartSession", "true");

                    Call<SessionModelResult> callSession = retrofitInterface.executeGetSession(sessionMap);

                    callSession.enqueue(new Callback<SessionModelResult>() {
                        @Override
                        public void onResponse(Call<SessionModelResult> callSession, Response<SessionModelResult> response) {

                            if(response.code() == 200){
                                session_id = response.body().getSession_id();
                                String startLoc = response.body().getStart_location();
                                if(start_location.equals(startLoc)){
                                    locationRadioButton01.setChecked(true);
                                } else {
                                    locationRadioButton02.setChecked(true);
                                }
                                String time = response.body().getStart_time();
                                System.out.println("Time : " + time);
                                String hhs = time.substring(0,2);
                                String mms = time.substring(3,5);
                                String AMorPMs = time.substring(6,8);

                                System.out.println("Hours: " + hhs);
                                System.out.println("Minutes: " + mms);
                                System.out.println("AM or PM : " + AMorPMs);
                                timeHourEditText.setText(hhs);
                                timeMinutesEditText.setText(mms);

                                if(AMorPMs.equals("AM")){
                                    amRadioButton.setChecked(true);
                                } else {
                                    pmRadioButton.setChecked(true);
                                }

                                isSessionStart = true;
                                setVisibility();
                                SetInactiveRecords();

                            } else if(response.code() == 404){
                                //Toast.makeText( getApplicationContext(), "Email or Password does not match.", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<SessionModelResult> callSession, Throwable t) {

                            Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


                } else if(response.code() == 404){
                    Toast.makeText( getApplicationContext(), "Email or Password does not match.", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<BusAndRouteResults> call, Throwable t) {

                Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

//If Session Not start Yet button Click working
        startSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Set Button Visibility.

                if(locationRadioButton01.isChecked() || locationRadioButton02.isChecked()){
                    int radioId = locationRadioGroup.getCheckedRadioButtonId();
                    locationRadioButton = findViewById(radioId);
                    driveSelectStartLocation = locationRadioButton.getText().toString();
                } else {
                    driveSelectStartLocation = "";
                }
                String AMorPM = "";
                if(amRadioButton.isChecked() || pmRadioButton.isChecked()){
                    int timeRadioId = timeRadioGroup.getCheckedRadioButtonId();
                    timeRadioButton = findViewById(timeRadioId);
                     AMorPM = timeRadioButton.getText().toString();
                } else {
                    AMorPM = "";
                }


                String hh = timeHourEditText.getText().toString();
                String mm = timeMinutesEditText.getText().toString();

                String finalTime = hh + ":" + mm + "." + AMorPM;
                String driverSelectedEndLocation = "";
                if(driveSelectStartLocation.equals(start_location)){
                    driverSelectedEndLocation = destination;
                } else {
                    driverSelectedEndLocation = start_location;
                }

                if(driveSelectStartLocation != "" && finalTime != ""){

                    HashMap<String ,String> map = new HashMap<>();
                    map.put("bus_id", bus_id);
                    map.put("driver_id", _id);
                    map.put("route_id", route_id);
                    map.put("start_location", driveSelectStartLocation);
                    map.put("destination", driverSelectedEndLocation);
                    map.put("route_no", route_no);
                    map.put("start_time", finalTime);
                    map.put("isStartSession", "true");

                    Call<SessionModelResult> call = retrofitInterface.executestartsession(map);

                    call.enqueue(new Callback<SessionModelResult>() {
                        @Override
                        public void onResponse(Call<SessionModelResult> call, Response<SessionModelResult> response) {

                            if(response.code() == 200){
                                session_id = response.body().getSession_id();
                                isSessionStart = true;
                                setVisibility();
                                Toast.makeText(getApplicationContext() , "Session Start Successfully!!!", Toast.LENGTH_LONG).show();
                                SetInactiveRecords();

                            } else if(response.code() == 400){
                                Toast.makeText(getApplicationContext() , "Can't start Session, Please login again!!!", Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<SessionModelResult> call, Throwable t) {
                            Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


                } else {
                    Toast.makeText(getApplicationContext() , "Please select start location and Time.", Toast.LENGTH_LONG).show();
                }



            }
        });

//End Session
        endSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String ,String> map = new HashMap<>();
                map.put("session_id", session_id);
                map.put("isStartSession", "false");

                Call<Void> call = retrofitInterface.executeEndSession(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if(response.code() == 200){
                            Toast.makeText(getApplicationContext() , "Session Closed!!!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), Driver_Home.class);
                            i.putExtra("email", email);
                            i.putExtra("_id", _id);
                            startActivity(i);


                        } else if(response.code() == 400){
                            Toast.makeText(getApplicationContext() , "Please login again!!!", Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
// See Passengers
        noOfPassengerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), PassengerList.class);
                i.putExtra("session_id", session_id);
                i.putExtra("bus_id", bus_id);
                startActivity(i);
            }
        });


        ButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), login.class);
                startActivity(i);
            }
        });


    }

    public void setVisibility(){
        if(isSessionStart){

            endSessionButton.setVisibility(View.VISIBLE);
            noOfPassengerButton.setVisibility(View.VISIBLE);
            paragraphTextView.setVisibility(View.VISIBLE);
            startSessionButton.setVisibility(View.GONE);

        } else {
            endSessionButton.setVisibility(View.GONE);
            noOfPassengerButton.setVisibility(View.GONE);
            paragraphTextView.setVisibility(View.GONE);
        }

    }

    public void SetInactiveRecords(){

        locationRadioGroup.setEnabled(false);
        timeRadioGroup.setEnabled(false);
        timeHourEditText.setEnabled(false);
        timeMinutesEditText.setEnabled(false);
    }
}