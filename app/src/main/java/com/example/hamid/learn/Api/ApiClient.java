package com.example.hamid.learn.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hamid on 7/16/2017.
 */

public class ApiClient {
    public static final String BASE_URL="http://www.hardroid.ir/shop/";

    private static Retrofit retrofit = null;
    private static String TAG=ApiClient.class.getSimpleName();


    public static Retrofit getClient() {


        if (retrofit==null) {
            OkHttpClient client=new OkHttpClient.Builder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }





}

