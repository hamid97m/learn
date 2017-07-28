package com.example.hamid.learn.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hamid.learn.Model.post;
import com.example.hamid.learn.R;
import com.example.hamid.learn.View.MainActivity;
import com.example.hamid.learn.View.Paymentactivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamid on 7/13/2017.
 */

public class Addapter extends RecyclerView.Adapter<Addapter.viewholder> {


    public LinearLayout linearLayout;
    public LinearLayout heartclick;

    private Context context;
    private List<post> posts = new ArrayList<>();


    public Addapter(Context context) {


        this.context = context;

    }

    public void addposts(List<post> posts) {
        this.posts = posts;
        notifyDataSetChanged();


    }


    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(final viewholder holder, int position) {


        holder.textView.setText(posts.get(position).getTextView());
        Picasso.with(context).load(posts.get(position).getImageView()).into(holder.imageView);
        holder.gheymat.setText(MainActivity.formatter.format(posts.get(position).getValue()));
        holder.mark.setText(posts.get(position).getMark());
        holder.summery.setText(posts.get(position).getSummery());
        if(posts.get(position).isFarvar()) {
            holder.heart.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.heart_after, null));
            holder.like.setText("مورد علاقه");
        }else {
            holder.heart.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.heart, null));
            holder.like.setText("");
        }




    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class viewholder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView gheymat;
        private TextView textView;
        private TextView mark;
        private TextView summery;
        private ImageView heart;
        private TextView like;


        public viewholder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_item);
            textView = (TextView) itemView.findViewById(R.id.textitem);
            mark = (TextView) itemView.findViewById(R.id.mark);
            summery = (TextView) itemView.findViewById(R.id.summery);
            like = (TextView) itemView.findViewById(R.id.like);
            gheymat = (TextView) itemView.findViewById(R.id.gheymat);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.relativ);
            heartclick = (LinearLayout) itemView.findViewById(R.id.heartclick);
            heart = (ImageView) itemView.findViewById(R.id.heart);
            //set font
            textView.setTypeface(MainActivity.b_typeface);
            mark.setTypeface(MainActivity.l_typeface);
            summery.setTypeface(MainActivity.n_typeface);
            gheymat.setTypeface(MainActivity.l_typeface);
            like.setTypeface(MainActivity.l_typeface);



            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, textView.getText() + "Coming soon !", 500).show();
                    Intent intent=new Intent(context, Paymentactivity.class);
//                    int value =Integer.parseInt(gheymat.getText().toString());posts.get(position).getValue()
                    double value =posts.get(getLayoutPosition()).getValue();
                    int hamid=(int)value;
                    String discription =textView.getText().toString() ;
                    String mobile ="09360528920" ;
                    String email ="shr.mahmoodi@gmail.com" ;
                    intent.putExtra("value", hamid);
                    intent.putExtra("discription", discription);
                    intent.putExtra("monile", mobile);
                    intent.putExtra("email", email);
                    context.startActivity(intent);
                }
            });
            heartclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(posts.get(getLayoutPosition()).isFarvar()==false){
                        posts.get(getLayoutPosition()).setFarvar(true);
                        notifyItemChanged(getLayoutPosition());
                    }else {
                        posts.get(getLayoutPosition()).setFarvar(false);
                        notifyItemChanged(getLayoutPosition());
                    }


                }
            });


        }


    }

}
