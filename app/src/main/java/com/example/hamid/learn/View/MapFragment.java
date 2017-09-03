package com.example.hamid.learn.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hamid.learn.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.victor.loading.rotate.RotateLoading;

/**
 * Created by Hamid on 8/18/2017.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private RotateLoading progressbar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        super.onViewCreated(view, savedInstanceState);
    }

    private MapView mapView;
    private GoogleMap googleMap;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng=new LatLng(DetailOfLocation.lat,DetailOfLocation.lng);
        CameraUpdate center= CameraUpdateFactory.newLatLng(latLng);
        CameraUpdate zoom= CameraUpdateFactory.zoomTo(10);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("مکان مورد نظره شما")).showInfoWindow();
        progressbar.stop();

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.map_fragment,container,false);

        progressbar=(RotateLoading)view.findViewById(R.id.mapreadyprogress);
        progressbar.start();

        return view;
    }



}
