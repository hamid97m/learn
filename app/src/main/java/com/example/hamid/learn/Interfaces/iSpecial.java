package com.example.hamid.learn.Interfaces;

import com.example.hamid.learn.Model.PostRetrofitModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Hamid on 9/1/2017.
 */

public interface iSpecial {
    @Headers({
            "content-type: application/x-www-form-urlencoded"

    })
    @GET("special.php")
    Call<PostRetrofitModel> getPosts();

    @Headers({
            "content-type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("special.php")
    public Call<PostRetrofitModel> insertUser(
            @Field("latitude") double latitude,
            @Field("longitude") double longitude,
            @Field("daste") String daste);
}
