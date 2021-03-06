package com.example.hamid.learn.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hamid.learn.Adapters.Addapter;
import com.example.hamid.learn.Api.SeenApi;
import com.example.hamid.learn.Interfaces.Onmahsoolatrecive;
import com.example.hamid.learn.Model.post;
import com.example.hamid.learn.R;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

import ss.com.infinitescrollprovider.InfiniteScrollProvider;
import ss.com.infinitescrollprovider.OnLoadMoreListener;

/**
 * Created by Hamid on 7/13/2017.
 */

public class SeenFragment extends Fragment {
    private RecyclerView recyclerView;
    private  View view;
    private  SeenApi api;
    public static Addapter SeenAdapter;
    private RotateLoading rotateLoading;
    private int page=0;

    private SwipeRefreshLayout swipeRefreshLayout;


    private List<post> postss;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("fragmmmmment",""+3);
        setRetainInstance(true);
        SeenAdapter=new Addapter(getActivity());
        api= new SeenApi();
        connect(0);
        SeenAdapter.clear();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment2,container,false);
        setuprecycelview();


        InfiniteScrollProvider infiniteScrollProvider=new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView, new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                rotateLoading.start();
                page++;
                connect(page);

            }
        });

        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swiprefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rotateLoading.start();
                SeenAdapter.clear();
                page=0;
                connect(page);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view ;


    }

    private void setuprecycelview() {
        recyclerView=(RecyclerView)view.findViewById(R.id.recycleview_seen);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL ));
        recyclerView.setAdapter(SeenAdapter);
        rotateLoading=(RotateLoading)view.findViewById(R.id.rotateloadingseen);
        if (postss == null) rotateLoading.start();

    }

    public static SeenFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SeenFragment fragment = new SeenFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void connect(int page){
        api.getmahsoolat(new Onmahsoolatrecive() {
            @Override
            public void onrecive(List<post> posts) {
                postss=posts;
                SeenAdapter.addposts(posts);

                rotateLoading.stop();

            }
        },MainActivity.daste,page,MainActivity.ostan);

    }


}
