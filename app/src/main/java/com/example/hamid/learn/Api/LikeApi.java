package com.example.hamid.learn.Api;

import android.support.design.widget.Snackbar;

import com.example.hamid.learn.Interfaces.Onmahsoolatrecive;
import com.example.hamid.learn.Interfaces.iLikeApi;
import com.example.hamid.learn.Model.PostRetrofitModel;
import com.example.hamid.learn.Model.mahsool;
import com.example.hamid.learn.Model.post;
import com.example.hamid.learn.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Hamid on 8/7/2017.
 */

public class LikeApi {
    public static List<post> finalpost=new ArrayList<>();
    public void getmahsoolat(final Onmahsoolatrecive onmahsoolatrecive, String title_post, int page){
        iLikeApi service= ApiClient.getClient().create(iLikeApi.class);
        Call<PostRetrofitModel> call=service.insertUser(title_post,page*8);
        call.enqueue(new retrofit2.Callback<PostRetrofitModel>() {
            @Override
            public void onResponse(Call<PostRetrofitModel> call, Response<PostRetrofitModel> response) {
                PostRetrofitModel result=response.body();
                ArrayList<mahsool> products=new ArrayList<>();
                if (result!=null) {
                    products = result.getPosts();

                    for (int i = 0; i < products.size(); i++) {
                        post newpost = new post();
                        newpost.setTextView(products.get(i).getName());
                        newpost.setId(products.get(i).getId());
                        newpost.setImageView(products.get(i).getImage());
                        newpost.setValue(products.get(i).getGheymat());
                        newpost.setMark(products.get(i).getMark());
                        newpost.setSummery(products.get(i).getSummery_dis());
                        newpost.setSeen(products.get(i).getSeen());
                        finalpost.add(newpost);
                    }
                    onmahsoolatrecive.onrecive(finalpost);
                }
                else{
                    Snackbar.make(MainActivity.coordinatorLayout,"خطا در ارتباط لطفا اینترنت خود را چک کنید",10000).show();
                }


            }

            @Override
            public void onFailure(Call<PostRetrofitModel> call, Throwable t) {



            }
        });
    }
}
