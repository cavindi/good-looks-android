package com.ludowica.goodlooks.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.model.User;
import com.ludowica.goodlooks.services.ApiClient;
import com.ludowica.goodlooks.services.AuthService;
import com.ludowica.goodlooks.services.CartService;
import com.ludowica.goodlooks.services.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText name;
    private EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.input_username);
        password = findViewById(R.id.input_name);
        name = findViewById(R.id.input_password);
        address = findViewById(R.id.input_address);
    }

    public void txtSignInClicked(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void btnRegisterClicked(View view) {

        if (!username.getText().toString().isEmpty() &&
                !password.getText().toString().isEmpty() &&
                !address.getText().toString().isEmpty() &&
                !name.getText().toString().isEmpty()
        ) {

            User user = new User();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.setAddress(address.getText().toString());
            user.setName(name.getText().toString());

            AuthService authService = ApiClient.getClient().create(AuthService.class);
            Call<User> call = authService.register(user);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    if (response.isSuccessful()) {

                        Toast.makeText(getApplicationContext(), "Register Successful!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }
}