package com.example.hamid.learn.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hamid.learn.Api.SeeAllInMap;
import com.example.hamid.learn.Interfaces.iOnLocationrecive;
import com.example.hamid.learn.Model.mapmodel;
import com.example.hamid.learn.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import static com.example.hamid.learn.R.id.map;

public class AllInMap extends AppCompatActivity  implements OnMapReadyCallback {
    private SeeAllInMap api;
    public GoogleMap ggoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_in_map);
        SupportMapFragment mapFrag= (SupportMapFragment)getSupportFragmentManager().findFragmentById(map);
        mapFrag.getMapAsync(this);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng=new LatLng(35.6991, 51.4281);
        CameraUpdate center= CameraUpdateFactory.newLatLng(latLng); CameraUpdate zoom=CameraUpdateFactory.zoomTo(12);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
       ggoogleMap=googleMap;
        api=new SeeAllInMap();
        api.getmahsoolat(new iOnLocationrecive() {


            @Override
            public void onrecive(List<mapmodel> posts) {
                for(int i=0;i<posts.size();i++) {
                        LatLng latLng = new LatLng(posts.get(i).getLatitude(), posts.get(i).getLongitude());
                    if(posts.get(i).getDaste().equals("cafee")) {
                        ggoogleMap.addMarker(new MarkerOptions().snippet(String.valueOf(posts.get(i).getId())).position(latLng).title(posts.get(i).getName())).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.cofee));
                    }
                    if(posts.get(i).getDaste().equals("resturant")) {
                        ggoogleMap.addMarker(new MarkerOptions().snippet(String.valueOf(posts.get(i).getId())).position(latLng).title(posts.get(i).getName())).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pizza));
                    }
                    if(posts.get(i).getDaste().equals("tarikhi")) {
                        ggoogleMap.addMarker(new MarkerOptions().snippet(String.valueOf(posts.get(i).getId())).position(latLng).title(posts.get(i).getName())).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.tarikhi));
                    }
                    if(posts.get(i).getDaste().equals("gardesh")) {
                        ggoogleMap.addMarker(new MarkerOptions().snippet(String.valueOf(posts.get(i).getId())).position(latLng).title(posts.get(i).getName())).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.park));
                    }
                    if(posts.get(i).getDaste().equals("kharid")) {
                        ggoogleMap.addMarker(new MarkerOptions().snippet(String.valueOf(posts.get(i).getId())).position(latLng).title(posts.get(i).getName())).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.shopping_bag));
                    }


                }

            }
        });
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                Intent intent=new Intent(AllInMap.this, DetailOfLocation.class);
                intent.putExtra("name",arg0.getTitle());
                intent.putExtra("id",Integer.parseInt(arg0.getSnippet()));
                startActivity(intent);
            }
        });


    }
    //TODO  remember COSTOMIZA ALL TOAST
    //TODO remmeber add filter for locations in AllInMapActivity

}
