package com.example.hamid.learn.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hamid.learn.Api.PlusLike;
import com.example.hamid.learn.Api.PlusSeen;
import com.example.hamid.learn.Model.post;
import com.example.hamid.learn.R;
import com.example.hamid.learn.View.DetailOfLocation;
import com.example.hamid.learn.View.FirstActivity;
import com.example.hamid.learn.View.MainActivity;
import com.example.hamid.learn.View.SaleActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hamid on 7/13/2017.
 */

public class Addapter extends RecyclerView.Adapter<Addapter.viewholder> {


    public ConstraintLayout linearLayout;
    public LinearLayout heartclick;

    private Context context;
    private List<post> posts = new ArrayList<>();


    public Addapter(Context context) {


        this.context = context;

    }

    public void clear(){
        this.posts.clear();
        notifyDataSetChanged();
    }


    public void addposts(List<post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }


    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(final viewholder holder, int position) {


        holder.textView.setText(posts.get(position).getTextView());
        Picasso.with(context).load(posts.get(position).getImageView()).resize(400,400).into(holder.imageView);
        if (posts.get(position).getValue()==0){
            holder.gheymat.setText("بدون تخفیف");
        }else {
            holder.gheymat.setText("تا "+"%"+(int)posts.get(position).getValue()+" تخفیف");
        }
        holder.mark.setText(posts.get(position).getMark());
        holder.summery.setText(posts.get(position).getSummery());
        holder.seen.setText(posts.get(position).getSeen());
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
        private TextView seen;
        private TextView mark;
        private TextView summery;
        private ImageView heart;
        private TextView like;


        public viewholder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_item);
            textView = (TextView) itemView.findViewById(R.id.textitem);
            seen = (TextView) itemView.findViewById(R.id.seen);
            mark = (TextView) itemView.findViewById(R.id.mark);
            summery = (TextView) itemView.findViewById(R.id.summery);
            like = (TextView) itemView.findViewById(R.id.like);
            gheymat = (TextView) itemView.findViewById(R.id.gheymat);
            linearLayout = (ConstraintLayout) itemView.findViewById(R.id.relativ);
            heartclick = (LinearLayout) itemView.findViewById(R.id.img_favar);
            heart = (ImageView) itemView.findViewById(R.id.heart);
            //set font
            textView.setTypeface(MainActivity.b_typeface);
            seen.setTypeface(MainActivity.l_typeface);
            mark.setTypeface(MainActivity.l_typeface);
            summery.setTypeface(MainActivity.n_typeface);
            gheymat.setTypeface(MainActivity.l_typeface);
            like.setTypeface(MainActivity.l_typeface);




            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlusSeen plusSeen=new PlusSeen();
                    plusSeen.plus(posts.get(getLayoutPosition()).getId());


                    if (FirstActivity.is_premium){
                    Snackbar.make(view, textView.getText() + "Coming soon !", 500).show();
                    Intent intent=new Intent(context, DetailOfLocation.class);
//                    double value =posts.get(getLayoutPosition()).getValue();
//                    int hamid=(int)value;
//                    String discription =textView.getText().toString() ;
//                    String mobile ="09360528920" ;
//                    intent.putExtra("value", hamid);
//                    intent.putExtra("discription", discription);
//                    intent.putExtra("monile", mobile);
//                    intent.putExtra("email", email);
                    String name=posts.get(getLayoutPosition()).getTextView();
                    String image =posts.get(getLayoutPosition()).getImageView() ;
                    int id=posts.get(getLayoutPosition()).getId();
                    Boolean favar=posts.get(getLayoutPosition()).isFarvar();
                    intent.putExtra("image", image);
                    intent.putExtra("name",name);
                    intent.putExtra("id",id);
                    intent.putExtra("favar",favar);
                    intent.putExtra("addres",posts.get(getLayoutPosition()).getMark());
                    context.startActivity(intent);}
                    else{
                        Intent intent=new Intent(context, SaleActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
            heartclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if(posts.get(getLayoutPosition()).isFarvar()==false){
                        posts.get(getLayoutPosition()).setFarvar(true);
                        notifyItemChanged(getLayoutPosition());
                        PlusLike plusLike=new PlusLike();
                        plusLike.plus(posts.get(getLayoutPosition()).getId(),1);
                    }else {
                        posts.get(getLayoutPosition()).setFarvar(false);
                        notifyItemChanged(getLayoutPosition());
                        PlusLike plusLike=new PlusLike();
                        plusLike.plus(posts.get(getLayoutPosition()).getId(),0);
                    }


                }
            });


        }


    }

}