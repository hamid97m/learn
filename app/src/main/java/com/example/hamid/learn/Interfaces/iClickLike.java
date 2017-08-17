package com.example.hamid.learn.Interfaces;

import com.example.hamid.learn.Model.PostRetrofitModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Hamid on 7/31/2017.
 */

public interface iClickLike {
    @Headers({
            "content-type: application/x-www-form-urlencoded"

    })
    @GET("likeplus.php")
    Call<PostRetrofitModel> getPosts();
    @Headers({
            "content-type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("likeplus.php")
    public Call<PostRetrofitModel> insertUser(
            @Field("id") int id, @Field("which") int which);
}
