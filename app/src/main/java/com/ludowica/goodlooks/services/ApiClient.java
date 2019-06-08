package com.ludowica.goodlooks.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //    public static final String BASE_URL = "http://10.0.3.2:8080/api/";
    public static final String BASE_URL = "http://good-looks.herokuapp.com/api/";

    private static Retrofit retrofit = null;
    private static OkHttpClient client = null;

    public static Retrofit getClient() {

        if (retrofit == null) {

            getOkHttpClient();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getOkHttpClient() {

        String authToken = SharedPreferencesManager.getAuthToken(MyApp.getAppContext());

        client = new OkHttpClient.Builder().addInterceptor(chain -> {

            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + authToken)
                    .build();
            return chain.proceed(newRequest);
        }).build();

        return client;
    }

    public static Retrofit getClientForToken() {

        Retrofit retrofitForAuth = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofitForAuth;
    }
}
