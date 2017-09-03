package com.example.hamid.learn.Api;

import android.util.Log;

import com.example.hamid.learn.Interfaces.iAddComment;
import com.example.hamid.learn.Model.PostRetrofitModel;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Hamid on 8/19/2017.
 */

public class NewCommentApi {
    public void addcoment(String name,String discription,Float rating,int location) {
        iAddComment service= ApiClient.getClient().create(iAddComment.class);
        Call<PostRetrofitModel> call=service.insertUser(name,discription,rating,location);
        call.enqueue(new retrofit2.Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {

            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

                Log.i("sag to ghabret","sag");

            }
        });
    }
}
