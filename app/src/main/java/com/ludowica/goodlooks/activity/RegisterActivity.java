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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText name;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.input_username);
        name = findViewById(R.id.input_name);
        password = findViewById(R.id.input_password);
        email = findViewById(R.id.input_address);
    }

    public void txtSignInClicked(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void btnRegisterClicked(View view) {
        if (!username.getText().toString().isEmpty() &&
                !password.getText().toString().isEmpty() &&
                !email.getText().toString().isEmpty() &&
                !name.getText().toString().isEmpty()
        ) {
            User user = new User();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.setEmail(email.getText().toString());
            user.setName(name.getText().toString());

            AuthService authService = ApiClient.getClient().create(AuthService.class);
            Call<User> call = authService.register(user);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Customer registered successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
        else if(username.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter username!", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_SHORT).show();
        }
        else if(email.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter email!", Toast.LENGTH_SHORT).show();
        }
        else if(name.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter name!", Toast.LENGTH_SHORT).show();
        }
        else if(username.getText().toString().isEmpty() && password.getText().toString().isEmpty() && email.getText().toString().isEmpty() && name.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Empty fields!", Toast.LENGTH_SHORT).show();
        }
    }
}
