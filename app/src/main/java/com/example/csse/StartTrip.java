package com.example.csse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartTrip extends AppCompatActivity {


    Button buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_trip);

        buttonHome = findViewById(R.id.buttonHome);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String msg = extras.getString("msg");


             Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        }


        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}