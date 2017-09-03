package com.example.hamid.learn.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hamid.learn.Adapters.Addapter;
import com.example.hamid.learn.Api.SpecialApi;
import com.example.hamid.learn.Interfaces.Onmahsoolatrecive;
import com.example.hamid.learn.Model.post;
import com.example.hamid.learn.R;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

import ss.com.infinitescrollprovider.InfiniteScrollProvider;
import ss.com.infinitescrollprovider.OnLoadMoreListener;

import static com.example.hamid.learn.R.id.txt_explain;

/**
 * Created by Hamid on 9/1/2017.
 */

public class SpecialFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private SpecialApi api;
    public static Addapter Specialdapter;
    private RotateLoading rotateLoading;
    private int page = 0;
    private List<post> postss;
    private double latitude;
    private  double longitude;

    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        Specialdapter = new Addapter(getActivity());
        api = new SpecialApi();
        Specialdapter.clear();

        SharedPreferences preferences_sava_status = this.getActivity().getSharedPreferences("save_location", 0);
        latitude = Double.longBitsToDouble(preferences_sava_status.getLong("Latitude", 0));
        if(latitude!=0){
            latitude = Double.longBitsToDouble(preferences_sava_status.getLong("Latitude", 0));
            longitude = Double.longBitsToDouble(preferences_sava_status.getLong("Longitude", 0));
            connect(0);
        }



    }

    @Nullable
    @Override
    public  View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.special_fragment_layout, container, false);

//        SharedPreferences preferences_sava_status = this.getActivity().getSharedPreferences("save_location", 0);
//         latitude = Double.longBitsToDouble(preferences_sava_status.getLong("Latitude", 0));
//         longitude = Double.longBitsToDouble(preferences_sava_status.getLong("Longitude", 0));
        TextView explain=(TextView)view.findViewById(txt_explain);
        explain.setTypeface(MainActivity.n_typeface);
        if(longitude!=0){
            LinearLayout ll_contain=(LinearLayout)view.findViewById(R.id.ll_contain);
            ll_contain.setVisibility(View.GONE);
            setuprecycelview();
        }

      Button btn_new_location=(Button)view.findViewById(R.id.btn_new_location) ;
        btn_new_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MyLocationActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });




        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swiprefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Specialdapter.clear();
                page=0;
                connect(page);
                rotateLoading.start();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;


    }

    private void setuprecycelview() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_like);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(Specialdapter);
        rotateLoading = (RotateLoading) view.findViewById(R.id.rotateloadingseen);
        InfiniteScrollProvider infiniteScrollProvider = new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView, new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                rotateLoading.start();
                page++;
                connect(page);

            }
        });

    }

    public static SpecialFragment newInstance() {

        Bundle args = new Bundle();

        SpecialFragment fragment = new SpecialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void connect(int page) {
        api.getmahsoolat(new Onmahsoolatrecive() {
            @Override
            public void onrecive(List<post> posts) {
                postss = posts;
                Specialdapter.addposts(posts);
                rotateLoading.stop();


            }
        }, latitude, longitude,MainActivity.daste);

    }


}
