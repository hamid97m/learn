package com.example.hamid.learn.Interfaces;

import com.example.hamid.learn.Model.postmodelallinmap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Hamid on 8/6/2017.
 */
public interface iAllInMap {
    @Headers({
            "content-type: application/x-www-form-urlencoded"

    })
    @GET("allinMap.php")
    Call<postmodelallinmap> getPosts();


}
