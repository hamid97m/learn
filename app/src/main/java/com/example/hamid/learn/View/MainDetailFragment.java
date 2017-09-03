package com.example.hamid.learn.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hamid.learn.Api.DetailLocationApi;
import com.example.hamid.learn.Interfaces.OnDetailRecieve;
import com.example.hamid.learn.R;
import com.squareup.picasso.Picasso;
import com.victor.loading.rotate.RotateLoading;

/**
 * Created by Hamid on 8/18/2017.
 */

public class MainDetailFragment extends Fragment {
    private RotateLoading progressbar;
    private  View view;
    private TextView txt_discription;
    private   TextView txt_adrres;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.main_detail_fragment_layout,container,false);

        progressbar=(RotateLoading)view.findViewById(R.id.progress);
        progressbar.start();

        TextView txt_name=(TextView)view.findViewById(R.id.txt_name) ;
        txt_discription=(TextView)view.findViewById(R.id.discription) ;

        TextView namelbl=(TextView)view.findViewById(R.id.textView4) ;
        TextView dislbl=(TextView)view.findViewById(R.id.textView6) ;

        namelbl.setTypeface(MainActivity.l_typeface);
        dislbl.setTypeface(MainActivity.l_typeface);



        txt_name.setTypeface(MainActivity.n_typeface);
        txt_discription.setTypeface(MainActivity.n_typeface);


       txt_adrres=(TextView)view.findViewById(R.id.txt_adrres) ;
        txt_adrres.setTypeface(MainActivity.n_typeface);



        txt_name.setText(DetailOfLocation.name_txt);



        DetailLocationApi detaillocationapi=new DetailLocationApi();
        detaillocationapi.getdetailes(new OnDetailRecieve() {
            @Override
            public void onrecive(String discription, double latitude, double longitude, String imageurl, String addres) {
                Picasso.with(getContext()).load(imageurl).resize(600,600).into(DetailOfLocation.image);
                txt_discription.setText(discription);
                txt_adrres.setText(addres);
                DetailOfLocation.lat=latitude;
                DetailOfLocation.lng=longitude;
                progressbar.stop();
            }

        },DetailOfLocation.id);



        return view;
    }
}
