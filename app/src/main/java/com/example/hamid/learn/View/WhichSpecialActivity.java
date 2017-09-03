package com.example.hamid.learn.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hamid.learn.R;

public class WhichSpecialActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_which_special);

        TextView textView10=(TextView)findViewById(R.id.textView10);
        TextView textView11=(TextView)findViewById(R.id.textView11);
        TextView textView12=(TextView)findViewById(R.id.textView12);
        TextView textView13=(TextView)findViewById(R.id.textView13);
        TextView textView20=(TextView)findViewById(R.id.textView20);
        TextView textView15=(TextView)findViewById(R.id.textView15);
        TextView textView16=(TextView)findViewById(R.id.textView16);
        TextView textView19=(TextView)findViewById(R.id.textView19);
        TextView textView18=(TextView)findViewById(R.id.textView18);
        TextView textView22=(TextView)findViewById(R.id.textView22);

        textView10.setTypeface(MainActivity.n_typeface);
        textView11.setTypeface(MainActivity.n_typeface);
        textView12.setTypeface(MainActivity.n_typeface);
        textView13.setTypeface(MainActivity.n_typeface);
        textView15.setTypeface(MainActivity.n_typeface);
        textView16.setTypeface(MainActivity.n_typeface);
        textView19.setTypeface(MainActivity.n_typeface);
        textView18.setTypeface(MainActivity.n_typeface);
        textView22.setTypeface(MainActivity.n_typeface);
        textView20.setTypeface(MainActivity.n_typeface);

        Button btn_adi=(Button)findViewById(R.id.btn_adi);
        btn_adi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WhichSpecialActivity.this,Upload.class);
                intent.putExtra("special",1);
                intent.putExtra("degreeofspecial",1);
                startActivity(intent);

            }
        });

        Button btn_khas=(Button)findViewById(R.id.btn_khas);
        btn_khas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WhichSpecialActivity.this,Upload.class);
                intent.putExtra("special",1);
                intent.putExtra("degreeofspecial",2);
                startActivity(intent);
            }
        });

        Button btn_vije=(Button)findViewById(R.id.btn_vije);
        btn_vije.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WhichSpecialActivity.this,Upload.class);
                intent.putExtra("special",1);
                intent.putExtra("degreeofspecial",3);
                startActivity(intent);
            }
        });





    }
}
