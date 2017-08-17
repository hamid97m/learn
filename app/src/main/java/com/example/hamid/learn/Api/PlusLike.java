package com.example.hamid.learn.Api;

import android.util.Log;

import com.example.hamid.learn.Interfaces.iClickLike;
import com.example.hamid.learn.Model.PostRetrofitModel;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Hamid on 7/31/2017.
 */

public class PlusLike {
    public void plus(int id,int which) {
        iClickLike service= ApiClient.getClient().create(iClickLike.class);
        Call<PostRetrofitModel> call=service.insertUser(id,which);
        call.enqueue(new retrofit2.Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {

            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {

                Log.i("sag","saaaaaaaaaag");
            }
        });
    }
}
