package com.example.hamid.learn.View;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.hamid.learn.Adapters.viewpageraddapter;
import com.example.hamid.learn.R;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity{
    public static int test=0;
    public static Typeface n_typeface;
    public static Typeface l_typeface;
    public static Typeface b_typeface;
    public static DecimalFormat formatter= new DecimalFormat("#,###,###");
    private Snackbar IsConnectSnackbar;
    private CoordinatorLayout coordinatorLayout;

    private ConnectingBrodcas connectingBrodcas;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setuodrawer();
        Set_Up_Main_Viewpager();

        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.Main_cordinator);

        b_typeface=Typeface.createFromAsset(getAssets(),"fonts/bfont.ttf");
        l_typeface=Typeface.createFromAsset(getAssets(),"fonts/lfont.ttf");
        n_typeface=Typeface.createFromAsset(getAssets(),"fonts/font.ttf");




        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"fab Button Clicked!!",1000).show();
            }
        });

        Button comming_soon=(Button) findViewById(R.id.commin_soon);
        comming_soon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"فروش مستقیم در آپدیت بعدی , با تخفیف های عجیب !!!",5000).show();
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        connectingBrodcas=new ConnectingBrodcas();
        registerReceiver(connectingBrodcas,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(connectingBrodcas);

    }

    private void setuodrawer(){
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.main_drawer);
        Toolbar toolbar=(Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


    }
    private void Set_Up_Main_Viewpager(){
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tab_learn);
        ViewPager viewPager=(ViewPager) findViewById(R.id.viewpager);
//        viewPager.setOffscreenPageLimit(3);
        viewpageraddapter addapter=new viewpageraddapter(getSupportFragmentManager());
        viewPager.setAdapter(addapter);
        tabLayout.setupWithViewPager(viewPager);

    }



    private class ConnectingBrodcas extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            boolean isconnect=networkInfo!=null&&networkInfo.isConnectedOrConnecting();
           if(isconnect){
               if(IsConnectSnackbar!=null)
                   IsConnectSnackbar.dismiss();


           }else {
               IsConnectSnackbar=IsConnectSnackbar.make(coordinatorLayout,"خطا در ارتباط , لطفا اینترنت خود را چک کنید",IsConnectSnackbar.LENGTH_INDEFINITE);
               IsConnectSnackbar.show();

           }


        }
    }
}
