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
import com.example.hamid.learn.Api.API;
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

public class NewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private  View view;
    private int page=0;
    private  API api;
    public static Addapter NewsAdapter;
    public  RotateLoading rotateLoading;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<post> postss;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        NewsAdapter=new Addapter(getActivity());
        api=new API();
        connect(0);
        NewsAdapter.clear();


    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.recylefragment,container,false);
        Log.i("onCreateView","الان اجرا شد");
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
                NewsAdapter.clear();
                rotateLoading.start();
                page=0;
                connect(page);
                swipeRefreshLayout.setRefreshing(false);

            }
        });


        return view;
    }


    public static NewsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void connect(int page){
        api.getmahsoolat(new Onmahsoolatrecive() {
            @Override
            public void onrecive(List<post> posts) {
                postss=posts;
                NewsAdapter.addposts(posts);
                rotateLoading.stop();


            }
        },MainActivity.daste,page,MainActivity.ostan);

    }

    public void setuprecycelview(){
        recyclerView=(RecyclerView)view.findViewById(R.id.recycleview_test);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL ));
        recyclerView.setAdapter(NewsAdapter);
        rotateLoading=(RotateLoading)view.findViewById(R.id.rotateloading);
        if (postss==null)rotateLoading.start();


    }


}
