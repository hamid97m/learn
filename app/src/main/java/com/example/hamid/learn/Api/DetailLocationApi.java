package com.example.hamid.learn.Api;

import android.support.design.widget.Snackbar;

import com.example.hamid.learn.Interfaces.OnDetailRecieve;
import com.example.hamid.learn.Interfaces.iDetailOfLocation;
import com.example.hamid.learn.Model.Detailmodel;
import com.example.hamid.learn.Model.alldetails;
import com.example.hamid.learn.View.MainActivity;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Hamid on 8/17/2017.
 */

public class DetailLocationApi {

    public void getdetailes(final OnDetailRecieve onDetailRecieve, int id){
        iDetailOfLocation service= ApiClient.getClient().create(iDetailOfLocation.class);

        Call<alldetails> call=service.insertUser(id);
        call.enqueue(new retrofit2.Callback<alldetails>() {
            @Override
            public void onResponse(Call<alldetails> call, Response<alldetails> response) {
                alldetails result=response.body();
                Detailmodel detailmodel=result.getDetailes();
                if (detailmodel!=null){
                    String description =detailmodel.getDiscription();
                    double longitude=detailmodel.getLongitude();
                    double latitude=detailmodel.getLatitude();
                    String image=detailmodel.getImage();
                    String addres=detailmodel.getAddress();
                    onDetailRecieve.onrecive(description,latitude,longitude,image,addres);



                  }
                else {
                    Snackbar.make(MainActivity.coordinatorLayout,"خطا در ارتباط لطفا اینترنت خود را چک کنید",10000).show();

                }


            }

            @Override
            public void onFailure(Call<alldetails> call, Throwable t) {

            }

        });
    }
}
