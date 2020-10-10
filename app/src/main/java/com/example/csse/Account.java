package com.example.csse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Account extends AppCompatActivity {

    private static final String BASE_URL = "http://192.168.1.7:3000";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        String email = "";
        String name = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             email = extras.getString("email");
             name = extras.getString("name");
        }

        Button submitBTN = this.findViewById(R.id.buttonSubmit);

        final TextView nameText = this.findViewById(R.id.nameTxt);
        final TextView emailText = this.findViewById(R.id.emailTxt);

        final EditText accountNoEdit = this.findViewById(R.id.accounNoeditTxt2);
        final EditText bankEdit = this.findViewById(R.id.bankEditText);
        final EditText branchEdit = this.findViewById(R.id.branchEditText);

        nameText.setText(name);
        emailText.setText(email);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(accountNoEdit.getText().toString() != ""){

                    HashMap<String ,String> map = new HashMap<>();
                    map.put("email", emailText.getText().toString());
                    map.put("bank_acc_no", accountNoEdit.getText().toString());
                    map.put("bank_name", bankEdit.getText().toString());
                    map.put("bank_branch", branchEdit.getText().toString());
                    map.put("amount", "5000");

                    Call<Void> call = retrofitInterface.executeaccountsubmit(map);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {

                            if(response.code() == 200){

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("email", emailText.getText().toString());
                                i.putExtra("name", nameText.getText().toString());
                                startActivity(i);

                            } else if(response.code() == 400){
                                Toast.makeText(getApplicationContext() , "Already Registered", Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText( getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext() , "Please Enter Account No", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}