package com.example.csse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.7:3000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        final Button loginBTN = this.findViewById(R.id.loginBtn);

        final EditText emailEdit = this.findViewById(R.id.editTextUsername);

        final EditText passwordEdit = this.findViewById(R.id.editTextPassword);

        findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){



                loginBTN.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {

                        HashMap<String ,String> map = new HashMap<>();
                        map.put("email", emailEdit.getText().toString());
                        map.put("password", passwordEdit.getText().toString());

                        Call<LoginResults> call = retrofitInterface.executeLogin(map);

                        call.enqueue(new Callback<LoginResults>() {
                            @Override
                            public void onResponse(Call<LoginResults> call, Response<LoginResults> response) {

                                //System.out.println(response.getClass());
                               // System.out.println(response.body().getEmail());
                                //System.out.println(call);


                                if(response.code() == 200){
                                    String userType  = response.body().getType();
                                    if(userType.equals("2")){
                                        Intent i = new Intent(getApplicationContext(), Driver_Home.class);
                                        i.putExtra("email", emailEdit.getText().toString());
                                        i.putExtra("_id", response.body().get_id());
                                        startActivity(i);
                                    } else if(userType.equals("3")){
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        i.putExtra("email", emailEdit.getText().toString());
                                        i.putExtra("_id", response.body().get_id());
                                        startActivity(i);
                                    }

                                } else if(response.code() == 404){
                                    Toast.makeText( getApplicationContext(), "Email or Password does not match.", Toast.LENGTH_LONG).show();
                                }



                            }

                            @Override
                            public void onFailure(Call<LoginResults> call, Throwable t) {

                                Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });

            }
        });

        final Button RegisterBTN = this.findViewById(R.id.RigisterBtn);

        RegisterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), register.class);
                startActivity(i);
            }
        });

    }





}