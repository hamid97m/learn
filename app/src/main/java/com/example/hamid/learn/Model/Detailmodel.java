package com.example.hamid.learn.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hamid on 8/17/2017.
 */

public class Detailmodel {
    @SerializedName("discription")
    private String discription;

    @SerializedName("address")
    private String address;

    @SerializedName("image")
    private String image;

        @SerializedName("latitude")
    private Double latitude;

    @SerializedName("longitude")
    private Double longitude;

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
