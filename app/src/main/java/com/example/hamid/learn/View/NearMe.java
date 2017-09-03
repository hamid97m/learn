package com.example.hamid.learn.View;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamid.learn.Adapters.Addapter;
import com.example.hamid.learn.Adapters.FirstAdapter;
import com.example.hamid.learn.Api.NearApi;
import com.example.hamid.learn.Interfaces.Onmahsoolatrecive;
import com.example.hamid.learn.Model.post;
import com.example.hamid.learn.R;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

public class NearMe extends AppCompatActivity implements LocationListener {

    private RecyclerView recyclerView;
    private NearApi api;
    private Addapter NewsAdapter;
    public RotateLoading rotateLoading;
    private TextView txt_finding;
    private static int REQUEST_CODE = 101;
    private List<post> postss;
    private int near;
    private  ViewPager viewPager;
    private  LocationManager mLocationManager;
    private String daste_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_me);

        txt_finding=(TextView)findViewById(R.id.txt_finding);
        final LinearLayout detail=(LinearLayout)findViewById(R.id.detail);
        final EditText edt_near=(EditText)findViewById(R.id.edt_near);
        Button btn_send=(Button)findViewById(R.id.btn_send);
        NewsAdapter = new Addapter(NearMe.this);

        viewPager=(ViewPager)findViewById(R.id.viewPagerr);
        PagerAdapter adapter = new FirstAdapter(this);
        viewPager.setAdapter(adapter);

        api = new NearApi();
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // at the first we check Gps provider
        if (!mLocationManager.isProviderEnabled(mLocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    NearMe.this);
            alertDialogBuilder
                    .setMessage("مکان یاب گوشی شما خاموش است , مایل هستید فعال شود ؟")
                    .setCancelable(false)
                    .setPositiveButton("بله",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Intent callGPSSettingIntent = new Intent(
                                            android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    NearMe.this.startActivity(callGPSSettingIntent);
                                }
                            });
            alertDialogBuilder.setNegativeButton("خیر",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Toast.makeText(NearMe.this,"برنامه قادر به شناسایی مکان دقیق شما نیست ! ",Toast.LENGTH_LONG).show();
                            NearMe.this.finish();
                        }
                    });
            AlertDialog alert = alertDialogBuilder.create();
            alert.show();

        }


        //________________________________________________________________________________
        // location in how many distance ?
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt_near.getText().length()!=0){
                    switch (viewPager.getCurrentItem()){
                        case 0:daste_title="tarikhi";break;
                        case 1:daste_title="kharid";break;
                        case 2:daste_title="resturant";break;
                        case 3:daste_title="gardesh";break;
                        case 4:daste_title="cafee";break;
                        case 5:daste_title="all";break;
                    }
                near=Integer.parseInt(edt_near.getText().toString());
                detail.setVisibility(View.INVISIBLE);
                setuprecycelview();
                setuplocationmaneger();
                }else Snackbar.make(view,"لطفا مسافت را مشخص کنید",2500).show();
            }
        });

//________________________________________________________________________________

    }

    private void setuplocationmaneger() {


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE);
            }
        }
        if (mLocationManager.isProviderEnabled(mLocationManager.GPS_PROVIDER)) {

            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null ) {
                onLocationChanged(location);}
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600000, 1, this);
        }
    }



    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.i("Location Changed", location.getLatitude() + " asdasdasdand " + location.getLongitude());
            NewsAdapter.clear();
            connect(location.getLatitude(), location.getLongitude(),near,daste_title);
        }
    }

    // Required functions
    public void onProviderDisabled(String arg0) {
    }

    public void onProviderEnabled(String arg0) {
        Log.i("Location Changed", " asdasdasdand " + "ready");
        setuplocationmaneger();
    }

    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        Log.i("Location Changed", " asdasdasdand " + "changestatus");
        setuplocationmaneger();
    }


    public void setuprecycelview() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleview_test);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(NewsAdapter);
        rotateLoading = (RotateLoading) findViewById(R.id.rotateloading);
        if (postss == null) rotateLoading.start();
        txt_finding.setVisibility(View.VISIBLE);
    }


    // for send Data to Db
    private void connect(double lat, double lng,int near,String daste) {
            api.getmahsoolat(new Onmahsoolatrecive() {
                @Override
                public void onrecive(List<post> posts) {
                    if(posts.size()>0){
                    postss = posts;
                    NewsAdapter.addposts(posts);
                    rotateLoading.stop();
                    txt_finding.setVisibility(View.INVISIBLE);
                }else{
                        rotateLoading.stop();
                        txt_finding.setText("چیزی یافت نشد ...");
                    }
                }

            }, lat, lng, near,daste);
    }


    //___________________________________________________
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == 0) {
                setuplocationmaneger();

            }
        }
    }
    //____________________________________________________
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (NewsAdapter != null) NewsAdapter.clear();
        NearMe.this.finish();
    }


}