package com.ludowica.goodlooks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ludowica.goodlooks.R;
import com.ludowica.goodlooks.model.JwtResponse;
import com.ludowica.goodlooks.model.User;
import com.ludowica.goodlooks.services.ApiClient;
import com.ludowica.goodlooks.services.AuthService;
import com.ludowica.goodlooks.services.CartService;
import com.ludowica.goodlooks.services.SharedPreferencesManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.input_username);
        password = findViewById(R.id.input_password);
    }

    public void txtSignupClicked(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void btnLoginClicked(View view) {

        if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {

            User user = new User();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());

            AuthService authService = ApiClient.getClientForToken().create(AuthService.class);
            Call<JwtResponse> call = authService.signIn(user);

            call.enqueue(new Callback<JwtResponse>() {
                @Override
                public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {

                    if (response.isSuccessful()) {
                        setValues(response.body());
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect credentials! Check again...", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JwtResponse> call, Throwable t) {

                }
            });
        }
    }

    private void setValues(JwtResponse jwtResponse) {

        SharedPreferencesManager.setUserId(this, jwtResponse.getUserId());
        SharedPreferencesManager.setUsername(this, jwtResponse.getUsername());
        SharedPreferencesManager.setAuthToken(this, jwtResponse.getAccessToken());

        new CartService().initGetCart(this);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
