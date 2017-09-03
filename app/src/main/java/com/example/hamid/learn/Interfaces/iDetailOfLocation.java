package com.example.hamid.learn.Interfaces;

import com.example.hamid.learn.Model.Detailmodel;
import com.example.hamid.learn.Model.alldetails;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Hamid on 8/17/2017.
 */

public interface iDetailOfLocation {
    @Headers({
            "content-type: application/x-www-form-urlencoded"

    })
    @GET("DetailOfLocation.php")
    Call<Detailmodel> getPosts();

    @Headers({
            "content-type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("DetailOfLocation.php")
    public Call<alldetails> insertUser(
            @Field("id") int id);
}
