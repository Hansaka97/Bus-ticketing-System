package com.example.csse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.csse.model.PassengerAccountModel;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class register extends AppCompatActivity {
    private static final String BASE_URL = "http://192.168.1.7:3000";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    //private String BASE_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button RegisterBTN = this.findViewById(R.id.buttonRegister);

        final EditText nameEdit = this.findViewById(R.id.editTextName);
        final EditText emailEdit = this.findViewById(R.id.emaileditTxt);
        final EditText nicEdit = this.findViewById(R.id.niceditText);
        final EditText phoneNoEdit = this.findViewById(R.id.phoneEditText);

        final EditText passwordEdit = this.findViewById(R.id.passwordeditTxt);
        final EditText confirmPasswordEdit = this.findViewById(R.id.reEnterpassword);

        final CheckBox aggrementChk = this.findViewById(R.id.checkBoxAggree);

        PassengerAccountModel accountModel = new PassengerAccountModel();
        accountModel.setName(nameEdit.getText().toString());
        accountModel.setEmail(emailEdit.getText().toString());


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        RegisterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   System.out.println(passwordEdit.getText().toString());
             //   System.out.println(confirmPasswordEdit.getText().toString());

                if(aggrementChk.isChecked() == true){
                    if(passwordEdit.getText().toString().equals(confirmPasswordEdit.getText().toString()) ){
                        HashMap<String ,String> map = new HashMap<>();
                        map.put("name", nameEdit.getText().toString());
                        map.put("email", emailEdit.getText().toString());
                        map.put("nic", nicEdit.getText().toString());
                        map.put("phoneNo", phoneNoEdit.getText().toString());
                        map.put("password", passwordEdit.getText().toString());
                        map.put("bank_acc_no", "");
                        map.put("bank_name", "");
                        map.put("bank_branch", "");
                        map.put("is_change", "0");
                        //type = 3 : Passenger
                        map.put("type", "3");
                        map.put("amount", "0");
                        //System.out.println(map);


                        Call<Void> call = retrofitInterface.executesignup(map);

                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                                if(response.code() == 200){

                                    Intent i = new Intent(getApplicationContext(), Account.class);
                                    i.putExtra("email", emailEdit.getText().toString());
                                    i.putExtra("name", nameEdit.getText().toString());
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
                        Toast.makeText(getApplicationContext() , "Confirm Password Does Not Match!!!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext() , "AGREED TO TERMS AND CONDITION FIRST", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}