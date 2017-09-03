package com.example.hamid.learn.Interfaces;

import com.example.hamid.learn.Model.PostRetrofitModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Hamid on 8/25/2017.
 */

public interface iSearchApi {
    @Headers({
            "content-type: application/x-www-form-urlencoded"

    })
    @GET("search.php")
    Call<PostRetrofitModel> getPosts();

    @Headers({
            "content-type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("search.php")
    public Call<PostRetrofitModel> insertUser(
            @Field("daste") String daste,@Field("name") String name, @Field("ostan") String ostan);
}
