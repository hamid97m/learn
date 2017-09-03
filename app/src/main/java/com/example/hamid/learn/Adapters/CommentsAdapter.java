package com.example.hamid.learn.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hamid.learn.Model.Comment;
import com.example.hamid.learn.R;
import com.example.hamid.learn.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamid on 8/19/2017.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.viewholder> {
    private Context context;
    private List<Comment> comments = new ArrayList<>();

    public CommentsAdapter(Context context) {
        this.context = context;
    }

    public void clear(){
        this.comments.clear();
        notifyDataSetChanged();
    }

    public void addcomments(List<Comment> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comments_item, parent, false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {

        holder.name.setText(comments.get(position).getName()+" گفته :");
        holder.discription.setText(comments.get(position).getDiscription());
        holder.ratingBar.setRating(comments.get(position).getRating());




    }

    @Override
    public int getItemCount() {
        return comments.size();
    }


    public static class viewholder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView discription;
        private RatingBar ratingBar;

        public viewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            discription = (TextView) itemView.findViewById(R.id.discription);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingbarr);

            name.setTypeface(MainActivity.b_typeface);
            discription.setTypeface(MainActivity.n_typeface);

        }
    }
}
