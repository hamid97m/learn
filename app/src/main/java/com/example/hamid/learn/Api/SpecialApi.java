package com.example.hamid.learn.Api;

import android.util.Log;

import com.example.hamid.learn.Interfaces.Onmahsoolatrecive;
import com.example.hamid.learn.Interfaces.iSpecial;
import com.example.hamid.learn.Model.PostRetrofitModel;
import com.example.hamid.learn.Model.mahsool;
import com.example.hamid.learn.Model.post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Hamid on 9/1/2017.
 */

public class SpecialApi {
    public static List<post> finalpost=new ArrayList<>();
    public void getmahsoolat(final Onmahsoolatrecive onmahsoolatrecive, double lat, double lng,String daste){
        iSpecial service= ApiClient.getClient().create(iSpecial.class);
        Call<PostRetrofitModel> call=service.insertUser(lat,lng,daste);
        call.enqueue(new retrofit2.Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                PostRetrofitModel result=response.body();
                ArrayList<mahsool> products=new ArrayList<>();
                if (result!=null){
                    products=result.getPosts();
                    for (int i = 0; i <products.size() ; i++) {
                        post newpost=new post();
                        newpost.setTextView(products.get(i).getName());
                        newpost.setImageView(products.get(i).getImage());
                        newpost.setValue(products.get(i).getGheymat());
                        newpost.setSeen(products.get(i).getSeen());
                        newpost.setMark(products.get(i).getMark());
                        newpost.setSummery(products.get(i).getSummery_dis());
                        newpost.setDaste(products.get(i).getDaste());
                        newpost.setId(products.get(i).getId());
                        Log.i("sadfasdasd","asfdfhfgujty"+products.get(i).getName());


                        finalpost.add(newpost);
                    }
                    onmahsoolatrecive.onrecive(finalpost);
                }else {
                    onmahsoolatrecive.onrecive(null);

                }
            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {
                Log.i("sadfasdasd","asfdfhfgujty"+"sag shod");
            }
        });
    }
}
