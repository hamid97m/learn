package com.example.hamid.learn.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hamid on 8/6/2017.
 */

public class allinmaplittle {
    @SerializedName("name")
    private String name;

    @SerializedName("latitude")
    private Double latitude;

    @SerializedName("longitude")
    private Double longitude;

    @SerializedName("daste")
    private String daste;

    @SerializedName("id")
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDaste() {
        return daste;
    }

    public void setDaste(String daste) {
        this.daste = daste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
