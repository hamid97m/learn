package com.example.hamid.learn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hamid on 8/17/2017.
 */

public class Version  implements Serializable {
    @SerializedName("version")
    private int verison;


    public int getVerison() {
        return verison;
    }

    public void setVerison(int verison) {
        this.verison = verison;
    }
}
