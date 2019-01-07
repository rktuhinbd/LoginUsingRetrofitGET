package com.multibrandinfotech.retrofitget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etUserName, etPassword;
    private Button btLogin;
    private ArrayList<String> userName = new ArrayList<String>();
    private ArrayList<String> password = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btLogin = (Button) findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseJSONData();
                String userNameText = String.valueOf(etUserName.getText());
                String passwordText = String.valueOf(etPassword.getText());

                if (userNameText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill-up all fields", Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < userName.size(); i++) {
                        if (userName.get(i).equals(userNameText) && password.get(i).equals(passwordText)) {
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                            return;
                        } else {
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    public void parseJSONData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Login>> call = api.loginData();

        call.enqueue(new Callback<List<Login>>() {
            @Override
            public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
                List<Login> logins = response.body();

                for (Login l : logins) {
                    userName.add(l.getUserName());
                    password.add(l.getPassword());
                }
            }

            @Override
            public void onFailure(Call<List<Login>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
