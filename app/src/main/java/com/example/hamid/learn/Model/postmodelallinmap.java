package com.example.hamid.learn.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hamid on 8/6/2017.
 */

public class postmodelallinmap {
    @SerializedName("mahsoolat")
    private ArrayList<allinmaplittle> posts=new ArrayList<>();


    public ArrayList<allinmaplittle> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<allinmaplittle> posts) {
        this.posts = posts;
    }
}
