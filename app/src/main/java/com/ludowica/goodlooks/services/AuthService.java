package com.ludowica.goodlooks.services;

import com.ludowica.goodlooks.model.JwtResponse;
import com.ludowica.goodlooks.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("auth/login")
    Call<JwtResponse> signIn(@Body User user);

    @POST("auth/register")
    Call<User> register(@Body User user);
}
