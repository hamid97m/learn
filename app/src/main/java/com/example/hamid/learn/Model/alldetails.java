package com.example.hamid.learn.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hamid on 8/18/2017.
 */

public class alldetails {
    @SerializedName("alldetails")
    private Detailmodel detailes=new Detailmodel();

    public Detailmodel getDetailes() {
        return detailes;
    }

    public void setDetailes(Detailmodel detailes) {
        this.detailes = detailes;
    }
}
