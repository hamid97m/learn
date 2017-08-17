package com.example.hamid.learn.View;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.hamid.learn.Adapters.viewpageraddapter;
import com.example.hamid.learn.R;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity{
    public static int test=0;
    public static Typeface n_typeface;
    public static Typeface l_typeface;
    public static Typeface b_typeface;
    public static DecimalFormat formatter= new DecimalFormat("#,###,###");
    private Snackbar IsConnectSnackbar;
    public static CoordinatorLayout coordinatorLayout;
    private ConnectingBrodcas connectingBrodcas;

    public static String daste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging fm = FirebaseMessaging.getInstance();
        String to = "AIzaSyAk5I8RyOwehxj-yIU3QDbK6xmE_HaZo5c"; // the notification key
        AtomicInteger msgId = new AtomicInteger();
        fm.send(new RemoteMessage.Builder(to)
                .setMessageId(String.valueOf(msgId))
                .addData("hello", "world")
                .build());


        setuodrawer();
        Set_Up_Main_Viewpager();
        SetupNavigationview();


        Bundle extras = getIntent().getExtras();
         daste= extras.getString("daste");

        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.Main_cordinator);

        b_typeface=Typeface.createFromAsset(getAssets(),"fonts/bfont.ttf");
        l_typeface=Typeface.createFromAsset(getAssets(),"fonts/lfont.ttf");
        n_typeface=Typeface.createFromAsset(getAssets(),"fonts/font.ttf");




        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Upload.class));
                overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
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

    private void SetupNavigationview() {
        NavigationView navigationview=(NavigationView)findViewById(R.id.mainnavigation);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.NewLocation:
                        startActivity(new Intent(MainActivity.this,Upload.class));
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                    case R.id.ShowAll :
                        startActivity(new Intent(MainActivity.this,AllInMap.class));
                        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
                        break;
                }
                return false;
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



//    // for exit in back and kill app
//    boolean doubleBackToExitPressedOnce = false;
//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            android.os.Process.killProcess(android.os.Process.myPid());
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
////        Toast.makeText(this, "برای خروج لطفا دکمه بازگشت را دوباره فشار دهید", Toast.LENGTH_SHORT).show();
//        LayoutInflater inflater = getLayoutInflater();
//        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.saggg));
//
//        ImageView image = (ImageView) layout.findViewById(R.id.image);
//        image.setImageResource(R.drawable.ic_settings_backup_restore_black_24dp);
//        TextView text = (TextView) layout.findViewById(R.id.text);
//        text.setTypeface(l_typeface);
//        text.setText("برای خروج لطفا دکمه بازگشت را دوباره فشار دهید");
//
//        Toast toast = new Toast(getApplicationContext());
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setView(layout);
//        toast.show();
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
        this.finish();
    }

}
