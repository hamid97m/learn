package com.example.hamid.learn.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamid.learn.Adapters.FirstAdapter;
import com.example.hamid.learn.Api.VersionApi;
import com.example.hamid.learn.BuildConfig;
import com.example.hamid.learn.Interfaces.OnVersionRecive;
import com.example.hamid.learn.R;

public class FirstActivity extends AppCompatActivity {
    private ViewPager viewPager;
    String daste_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        TextView txt_update=(TextView)findViewById(R.id.txt_update);
        txt_update.setTypeface(MainActivity.n_typeface);



        viewPager=(ViewPager)findViewById(R.id.viewPagerr);
        PagerAdapter adapter = new FirstAdapter(this);
        viewPager.setAdapter(adapter);

        Button letsgo=(Button)findViewById(R.id.letsgo);
        letsgo.setTypeface(MainActivity.n_typeface);


        VersionApi versionApi=new VersionApi();
        versionApi.CHECK(new OnVersionRecive() {
            @Override
            public void OnRecive(int version) {
                int CurrentVersion= BuildConfig.VERSION_CODE;
                if(version>CurrentVersion){
                   RelativeLayout update=(RelativeLayout)findViewById(R.id.update);
                    update.setVisibility(View.VISIBLE);
                }
            }
        });




        letsgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                switch (viewPager.getCurrentItem()){
                    case 0:daste_title="tarikhi";break;
                    case 1:daste_title="kharid";break;
                    case 2:daste_title="resturant";break;
                    case 3:daste_title="gardesh";break;
                    case 4:daste_title="cafee";break;
                    case 5:daste_title="all";break;
                }
                intent.putExtra("daste", daste_title);
                startActivity(intent);
            }
        });



    }


    // for exit in back and kill app
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            android.os.Process.killProcess(android.os.Process.myPid());
            return;
        }

        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "برای خروج لطفا دکمه بازگشت را دوباره فشار دهید", Toast.LENGTH_SHORT).show();
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.saggg));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_settings_backup_restore_black_24dp);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setTypeface(MainActivity.l_typeface);
        text.setText("برای خروج لطفا دکمه بازگشت را دوباره فشار دهید");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    private float mScale = 0.8f;
    private class ScalePageTransformer implements ViewPager.PageTransformer{
        @Override
        public void transformPage(View view, float position) {
            if(position==0){
                ViewCompat.setScaleX(view, 1);
                ViewCompat.setScaleY(view, 1);
            }
            else{
                ViewCompat.setScaleX(view, mScale);
                ViewCompat.setScaleY(view, mScale);
            }
        }
    }
}
