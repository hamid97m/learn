package com.example.hamid.learn.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid.learn.R;
import com.squareup.picasso.Picasso;

public class DetailOfLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_location);
        Bundle extras = getIntent().getExtras();
        ImageView image=(ImageView)findViewById(R.id.image);
        TextView txt_name=(TextView)findViewById(R.id.txt_name) ;

        String imageurl= extras.getString("image");
        String name_txt= extras.getString("name");
        txt_name.setText(name_txt);
        int id=extras.getInt("id");




        Picasso.with(this).load(imageurl).resize(600,600).into(image);

    }
}
