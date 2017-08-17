package com.example.hamid.learn.Api;

import android.util.Log;

import com.example.hamid.learn.Interfaces.OnVersionRecive;
import com.example.hamid.learn.Interfaces.iUpdateCheck;
import com.example.hamid.learn.Model.Version;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Hamid on 8/17/2017.
 */

public class VersionApi {
    public void CHECK(final OnVersionRecive onVersionRecive) {
        iUpdateCheck service= ApiClient.getClient().create(iUpdateCheck.class);
        Call<Version> call=service.getPosts();
        call.enqueue(new retrofit2.Callback<Version>() {
            @Override
            public void onResponse(Call<Version> call, Response<Version> response) {
                Version result=response.body();
                int NewVersion=result.getVerison();
                onVersionRecive.OnRecive(NewVersion);

            }

            @Override
            public void onFailure(Call<Version> call, Throwable t) {

                Log.i("retrofit","checkupdate");

            }
        });
    }
}
