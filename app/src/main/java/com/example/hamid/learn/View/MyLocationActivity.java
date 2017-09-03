package com.example.hamid.learn.View;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamid.learn.R;
import com.victor.loading.rotate.RotateLoading;

public class MyLocationActivity  extends AppCompatActivity implements LocationListener {

    public RotateLoading rotateLoading;
    private static int REQUEST_CODE = 101;
    private LocationManager mLocationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // at the first we check Gps provider
        if (!mLocationManager.isProviderEnabled(mLocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    MyLocationActivity.this);
            alertDialogBuilder
                    .setMessage("مکان یاب گوشی شما خاموش است , مایل هستید فعال شود ؟")
                    .setCancelable(false)
                    .setPositiveButton("بله",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Intent callGPSSettingIntent = new Intent(
                                            android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    MyLocationActivity.this.startActivity(callGPSSettingIntent);
                                }
                            });
            alertDialogBuilder.setNegativeButton("خیر",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Toast.makeText(MyLocationActivity.this,"برنامه قادر به شناسایی مکان دقیق شما نیست ! ",Toast.LENGTH_LONG).show();
                            MyLocationActivity.this.finish();
                        }
                    });
            AlertDialog alert = alertDialogBuilder.create();
            alert.show();

        }


        //________________________________________________________________________________
        // location in how many distance ?
        Button btn_find=(Button)findViewById(R.id.btn_find) ;
        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txt_finding=(TextView)findViewById(R.id.txt_finding);
                txt_finding.setVisibility(View.VISIBLE);
                RotateLoading loading=(RotateLoading)findViewById(R.id.rotateloading);
                loading.start();
                    setuplocationmaneger();
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
            SharedPreferences preferences_sava_status = getSharedPreferences("save_location", 0);
            SharedPreferences.Editor editor_sava_status = preferences_sava_status.edit();
            editor_sava_status.putLong("Latitude", Double.doubleToLongBits(location.getLatitude()));
            editor_sava_status.putLong("Longitude", Double.doubleToLongBits(location.getLongitude()));
            editor_sava_status.apply();
            Toast.makeText(MyLocationActivity.this,"با موفقعیت انجام شد , لطفا مجددا به بخش ویژه ترین بروید ",Toast.LENGTH_LONG).show();
            MyLocationActivity.this.finish();
        }
    }

    // Required functions
    public void onProviderDisabled(String arg0) {
    }

    public void onProviderEnabled(String arg0) {
        Log.i("Location Changed", " asdasdasdand " + "ready");

    }

    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        Log.i("Location Changed", " asdasdasdand " + "changestatus");

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
        MyLocationActivity.this.finish();
    }


}