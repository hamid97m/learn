package com.example.hamid.learn.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hamid on 8/19/2017.
 */

public class Comment {
    @SerializedName("name")
    private String name;

    @SerializedName("discription")
    private String discription;

    @SerializedName("rating")
    private Float rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public float getRating() {
        return rating;
    }


    public void setRating(Float rating) {
        this.rating = rating;
    }
}
