package com.example.hamid.learn.Api;

import android.support.design.widget.Snackbar;

import com.example.hamid.learn.Interfaces.iAllInMap;
import com.example.hamid.learn.Interfaces.iOnLocationrecive;
import com.example.hamid.learn.Model.allinmaplittle;
import com.example.hamid.learn.Model.mapmodel;
import com.example.hamid.learn.Model.postmodelallinmap;
import com.example.hamid.learn.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Hamid on 8/6/2017.
 */

public class SeeAllInMap {

    public static List<mapmodel> finalpost=new ArrayList<>();
    public void getmahsoolat(final iOnLocationrecive onmahsoolatrecive){
        iAllInMap service= ApiClient.getClient().create(iAllInMap.class);
        Call<postmodelallinmap> call=service.getPosts();
        call.enqueue(new retrofit2.Callback<postmodelallinmap>() {
            @Override
            public void onResponse(Call<postmodelallinmap> call, Response<postmodelallinmap> response) {
                postmodelallinmap result=response.body();
                ArrayList<allinmaplittle> products=new ArrayList<>();
                if (result!=null){
                    products=result.getPosts();

                    for (int i = 0; i <products.size() ; i++) {
                        mapmodel newpost=new mapmodel();
                        newpost.setName(products.get(i).getName());
                        newpost.setDaste(products.get(i).getDaste());
                        newpost.setId(products.get(i).getId());

                        if(products.get(i).getLatitude()!=null) {
                        newpost.setLatitude(products.get(i).getLatitude());
                        newpost.setLongitude(products.get(i).getLongitude());
                        }
                        finalpost.add(newpost);
                    }
                    onmahsoolatrecive.onrecive(finalpost);}
                else {
                    Snackbar.make(MainActivity.coordinatorLayout,"خطا در ارتباط لطفا اینترنت خود را چک کنید",10000).show();

                }


            }

            @Override
            public void onFailure(Call<postmodelallinmap> call, Throwable t) {



            }
        });
    }

}
