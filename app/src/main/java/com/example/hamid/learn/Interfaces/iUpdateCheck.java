package com.example.hamid.learn.Interfaces;

import com.example.hamid.learn.Model.Version;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Hamid on 8/17/2017.
 */

public interface iUpdateCheck {
    @Headers({
            "content-type: application/x-www-form-urlencoded"

    })
    @GET("updateversion.php")
    Call<Version> getPosts();
}
