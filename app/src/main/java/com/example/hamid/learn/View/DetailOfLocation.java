package com.example.hamid.learn.View;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid.learn.Api.PlusLike;
import com.example.hamid.learn.R;

public class DetailOfLocation extends AppCompatActivity {


    public static  String name_txt;
    public static  String addres_txt;

    public static int id;

    private TextView txt_toolbar_name;

    public static double lat;
    public static double lng;

    public  static   ImageView image;

    private  Boolean favar;

    private  ImageView img_favar;
    public static int checkfragment=0;

    public static   CommentsFragment commentsFragment;

    public static  FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_location);

        checkfragment=0;

        image=(ImageView)findViewById(R.id.image);
        Bundle extras = getIntent().getExtras();
        name_txt= extras.getString("name");
        id=extras.getInt("id");
        if (extras.getBoolean("favar")){
            favar=extras.getBoolean("favar");}

        commentsFragment=new CommentsFragment();
        final MainDetailFragment mainDetailFragment=new MainDetailFragment();


        Button btn_map=(Button) findViewById(R.id.map);

        Button detail=(Button) findViewById(R.id.detail);

        Button btn_comment=(Button) findViewById(R.id.btn_comment);

        txt_toolbar_name=(TextView)findViewById(R.id.name_toolbar);

        img_favar=(ImageView) findViewById(R.id.img_favar);






        fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmenrcontainer,new MainDetailFragment());
        fragmentTransaction.commit();

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction replace=fragmentManager.beginTransaction();
                replace.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                replace.replace(R.id.fragmenrcontainer,new MapFragment());
                replace.commit();
            }
        });

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction replace=fragmentManager.beginTransaction();
                replace.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                replace.replace(R.id.fragmenrcontainer,mainDetailFragment);
                replace.commit();
            }
        });

        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction replace=fragmentManager.beginTransaction();
                replace.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                replace.replace(R.id.fragmenrcontainer,commentsFragment);
                replace.addToBackStack(null);
                replace.commit();
            }
        });



        if (favar!=null){
            img_favar.setImageDrawable(ResourcesCompat.getDrawable(DetailOfLocation.this.getResources(), R.drawable.heart_after, null));
        }else{
            favar=false;
        }
        img_favar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(favar==false){
                    favar=true;
                    PlusLike plusLike=new PlusLike();
                    plusLike.plus(id,1);
                    img_favar.setImageDrawable(ResourcesCompat.getDrawable(DetailOfLocation.this.getResources(), R.drawable.heart_after, null));

                }else {
                    favar=false;
                    PlusLike plusLike=new PlusLike();
                    plusLike.plus(id,0);
                    img_favar.setImageDrawable(ResourcesCompat.getDrawable(DetailOfLocation.this.getResources(), R.drawable.heart, null));
                }
            }
        });



        txt_toolbar_name.setText(name_txt);



//        DetailLocationApi detaillocationapi=new DetailLocationApi();
//        detaillocationapi.getdetailes(new OnDetailRecieve() {
//            @Override
//            public void onrecive(String discription, double latitude, double longitude, String imageurl, String addres) {
//                Picasso.with(DetailOfLocation.this).load(imageurl).resize(600,600).into(image);
//                addres_txt = addres;
//            }
//
//        },id);



    }



    @Override
    public void onBackPressed()
    {

        if (  CommentsFragment.addapter!=null) CommentsFragment.addapter.clear();

            DetailOfLocation.this.finish();


    }
}
