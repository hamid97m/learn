package com.example.hamid.learn.Interfaces;

import com.example.hamid.learn.Model.CommentRetrofitModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Hamid on 8/19/2017.
 */

public interface iCommentApi {

    @Headers({
            "content-type: application/x-www-form-urlencoded"

    })
    @GET("comments.php")
    Call<CommentRetrofitModel> getPosts();

    @Headers({
            "content-type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("comments.php")
    public Call<CommentRetrofitModel> insertUser(
            @Field("location") int location, @Field("limit") int lim);

}
