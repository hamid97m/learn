package com.example.hamid.learn.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Hamid on 8/19/2017.
 */

public class CommentRetrofitModel {

    @SerializedName("comments")
    private ArrayList<Comment> comments=new ArrayList<>();


    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
