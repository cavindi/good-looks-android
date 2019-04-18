package com.ludowica.goodlooks.services;

import com.ludowica.goodlooks.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("auth")
    Call<User> signIn(@Body User user);

//    @POST("auth/signup")
//    Call<User> signUp(@Body User user);
}
