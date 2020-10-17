package com.example.csse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.csse.adapter.Adapter;
import com.example.csse.model.BusAndRouteResults;
import com.example.csse.model.PassengerLayoutModel;
import com.example.csse.model.SessionModelResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PassengerList extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.7:3000";


    private RecyclerView recyclerView;

    private String session_id;
    private String bus_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_list);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            session_id = extras.getString("session_id");
            bus_id = extras.getString("bus_id");
        }

        recyclerView = findViewById(R.id.listView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String ,String> map = new HashMap<>();
        map.put("session_id", session_id);
        map.put("bus_id", bus_id);
        Call<List<PassengerLayoutModel>> call = retrofitInterface.executegetpassengers(map);

        call.enqueue(new Callback<List<PassengerLayoutModel>>() {
            @Override
            public void onResponse(Call<List<PassengerLayoutModel>> call, Response<List<PassengerLayoutModel>> response) {

                if(response.code() == 200){

                    if(response.body() != null){
                        Adapter adapter = new Adapter(response.body());
                        recyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText( getApplicationContext(), "No Passengers in this Session", Toast.LENGTH_LONG).show();
                    }




                } else if(response.code() == 404){
                    Toast.makeText( getApplicationContext(), "No Session Started!!!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<PassengerLayoutModel>> call, Throwable t) {

                Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}