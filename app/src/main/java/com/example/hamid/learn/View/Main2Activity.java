package com.example.hamid.learn.View;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.hamid.learn.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.hamid.learn.R.id.map;

public class Main2Activity extends AppCompatActivity implements OnMapReadyCallback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SetToolbar();

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
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng=new LatLng(35.6991, 51.4281);
        CameraUpdate center= CameraUpdateFactory.newLatLng(latLng); CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
//        LatLng latLng=new LatLng(35.6991, 51.4281);
        googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.cofee)).title("کافه کتاب"));

        LatLng latLngg=new LatLng(35.6503, 51.4463);
        googleMap.addMarker(new MarkerOptions().position(latLngg).icon(BitmapDescriptorFactory.fromResource(R.drawable.cofee)).title("کافه هویج"));


        LatLng latLnggg=new LatLng(35.6166, 51.4236);
        googleMap.addMarker(new MarkerOptions().position(latLnggg).icon(BitmapDescriptorFactory.fromResource(R.drawable.cofee)).title("کافه تریا دانشگاه تهران"));


        LatLng latLngggg=new LatLng(35.6911, 51.4319);
        googleMap.addMarker(new MarkerOptions().position(latLngggg).icon(BitmapDescriptorFactory.fromResource(R.drawable.cofee)).title("کافه سلام"));



    }
}
