package com.example.hamid.learn.Api;

import com.example.hamid.learn.Interfaces.OnCommentsRecieve;
import com.example.hamid.learn.Interfaces.iCommentApi;
import com.example.hamid.learn.Model.Comment;
import com.example.hamid.learn.Model.CommentRetrofitModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Hamid on 8/19/2017.
 */

public class CommentsApi {
    public static List<Comment> finalcomments=new ArrayList<>();
    public void getcomments(final OnCommentsRecieve onCommentsRecieve, int location, int page){
        iCommentApi service= ApiClient.getClient().create(iCommentApi.class);
        Call<CommentRetrofitModel> call=service.insertUser(location,page*8);
        call.enqueue(new retrofit2.Callback<CommentRetrofitModel>() {
            @Override
            public void onResponse(Call<CommentRetrofitModel> call, Response<CommentRetrofitModel> response) {
                CommentRetrofitModel result=response.body();
                ArrayList<Comment> products=new ArrayList<>();
                if (result!=null){
                    products=result.getComments();

                    for (int i = 0; i <products.size() ; i++) {
                        Comment newcomment=new Comment();
                        newcomment.setName(products.get(i).getName());
                        newcomment.setDiscription(products.get(i).getDiscription());
                        newcomment.setRating(products.get(i).getRating());


                        finalcomments.add(newcomment);
                    }
                    onCommentsRecieve.onrecive(finalcomments);}
            }

            @Override
            public void onFailure(Call<CommentRetrofitModel> call, Throwable t) {

            }

        });
    }
}


