package com.example.hamid.learn.Interfaces;

import com.example.hamid.learn.Model.PostRetrofitModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Hamid on 8/21/2017.
 */

public interface iNearApi {
    @Headers({
            "content-type: application/x-www-form-urlencoded"

    })
    @GET("NearMe.php")
    Call<PostRetrofitModel> getPosts();

    @Headers({
            "content-type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("NearMe.php")
    public Call<PostRetrofitModel> insertUser(
            @Field("latitude") double latitude, @Field("longitude") double longitude,@Field("nearkm") int nearkm
            ,@Field("daste") String daste);
}
