package com.example.hamid.learn.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hamid on 7/16/2017.
 */

public class PostRetrofitModel implements Serializable {
    @SerializedName("mahsoolat")
    private ArrayList<mahsool> posts=new ArrayList<>();


    public ArrayList<mahsool> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<mahsool> posts) {
        this.posts = posts;
    }
}
