package com.example.hamid.learn.View;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.hamid.learn.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.hamid.learn.R.id.map;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Marker location;
    private Button backToUpload;
    private Snackbar information;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mapupload);
        SetToolbar();
        backToUpload=(Button)findViewById(R.id.btn_back_upload);
        backToUpload.setTypeface(MainActivity.n_typeface);
        backToUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RelativeLayout drawer=(RelativeLayout) findViewById(R.id.drawer);
       information= Snackbar.make(drawer,"برای ثبت روی مکان مورد نظر برای چند لحظه نگه دارید",information.LENGTH_INDEFINITE);
        information.show();
        SupportMapFragment mapFrag= (SupportMapFragment)getSupportFragmentManager().findFragmentById(map);
        mapFrag.getMapAsync(this);





    }

    private void SetToolbar() {
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        LatLng latLng=new LatLng(35.6991, 51.4281);
        CameraUpdate center= CameraUpdateFactory.newLatLng(latLng); CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);


        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
             information.dismiss();
                Upload.ll_map.setBackgroundResource(R.drawable.border_after);
             backToUpload.setVisibility(View.VISIBLE);
                if (location!=null)
                    location.remove();
                location= googleMap.addMarker(new MarkerOptions().position(latLng).title("مکان مورد نظره شما"));
                location.showInfoWindow();

                Upload.latitude=latLng.latitude;
                Upload.longitude=latLng.longitude;

            }
        });



    }
}
