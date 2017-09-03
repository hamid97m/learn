package com.example.hamid.learn.Interfaces;

import com.example.hamid.learn.Model.PostRetrofitModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Hamid on 8/19/2017.
 */

public interface iAddComment {
    @Headers({
            "content-type: application/x-www-form-urlencoded"

    })
    @GET("newcomment.php")
    Call<PostRetrofitModel> getPosts();
    @Headers({
            "content-type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("newcomment.php")
    public Call<PostRetrofitModel> insertUser(
            @Field("name") String name,@Field("discription") String discription,@Field("rating") Float rating,@Field("location") int location);
}
