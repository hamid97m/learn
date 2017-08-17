package com.example.hamid.learn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Hamid on 7/16/2017.
 */

public class mahsool implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

//    @SerializedName("latitude")
//    private Double latitude;
//
//    @SerializedName("longitude")
//    private Double longitude;


    @SerializedName("mark")
    private String mark;

    @SerializedName("seen")
    private String seen;

    @SerializedName("sumerry_dis")
    private String summery_dis;

    @SerializedName("daste")
    private String daste;

    @SerializedName("value")
    private double gheymat;

    @SerializedName("image")
    private String image;

    public mahsool(String daste,String name, double gheymatm,String image) {
        this.name = name;
        this.daste = daste;
        this.gheymat = gheymatm;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDaste() {
        return daste;
    }

    public void setDaste(String daste) {
        this.daste = daste;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getSummery_dis() {
        return summery_dis;
    }

    public void setSummery_dis(String summery_dis) {
        this.summery_dis = summery_dis;
    }

    public double getGheymat() {
        return gheymat;
    }

    public void setGheymat(double gheymat) {
        this.gheymat = gheymat;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(Double longitude) {
//        this.longitude = longitude;
//    }
//
//    public Double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(Double latitude) {
//        this.latitude = latitude;
//    }
}
