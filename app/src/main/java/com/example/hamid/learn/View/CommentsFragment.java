package com.example.hamid.learn.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hamid.learn.Adapters.CommentsAdapter;
import com.example.hamid.learn.Api.CommentsApi;
import com.example.hamid.learn.Interfaces.OnCommentsRecieve;
import com.example.hamid.learn.Model.Comment;
import com.example.hamid.learn.R;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

import ss.com.infinitescrollprovider.InfiniteScrollProvider;
import ss.com.infinitescrollprovider.OnLoadMoreListener;

/**
 * Created by Hamid on 8/19/2017.
 */

public class CommentsFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private int page=0;
    private CommentsApi api;
    private  TextView txt_nocomment;
    public static CommentsAdapter addapter;
    public RotateLoading rotateLoading;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Comment> maincomments;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("asdasd","gayidemanooncreat");

        addapter=new CommentsAdapter(getActivity());
//        api=new CommentsApi();



    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.comments_layout_fragment,container,false);


        txt_nocomment=(TextView)view.findViewById(R.id.txt_nocomment);
        txt_nocomment.setTypeface(MainActivity.n_typeface);



        Log.i("asdasd","gayidemanooncreatview");

        FloatingActionButton fab_new_comment=(FloatingActionButton)view.findViewById(R.id.fab_new_comment);
        fab_new_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction replace=DetailOfLocation.fragmentManager.beginTransaction();
                replace.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                replace.replace(R.id.fragmenrcontainer,new NewCommentFragment());
                replace.commit();
            }
        });
        Log.i("asdasd",""+DetailOfLocation.checkfragment);
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
                addapter.clear();
                rotateLoading.start();
                page=0;
                connect(page);
                swipeRefreshLayout.setRefreshing(false);

            }
        });


        return view;
    }




    private void connect(int page){
//        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        CommentsApi api=new CommentsApi();
        api.getcomments(new OnCommentsRecieve() {


            @Override
            public void onrecive(List<Comment> comments) {
                addapter.addcomments(comments);
                maincomments=comments;
                rotateLoading.stop();
                if (maincomments.size()==0) {
                    txt_nocomment.setVisibility(View.VISIBLE);
                    Snackbar.make(view,"اولین نظر را شما ثبت کنید !",3000).show();}
                else txt_nocomment.setVisibility(View.INVISIBLE);

                Log.i("asd",""+maincomments.size());
            }
        },DetailOfLocation.id,page);
    }

    public void setuprecycelview(){
        recyclerView=(RecyclerView)view.findViewById(R.id.recycleview_comments);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL ));

        recyclerView.setAdapter(addapter);
        rotateLoading=(RotateLoading)view.findViewById(R.id.rotateloadingcomment);
        if (maincomments==null){
            rotateLoading.start();
        }


        if(DetailOfLocation.checkfragment==0){
            addapter.clear();
            connect(0);
            DetailOfLocation.checkfragment=1;
        }

    }



}
