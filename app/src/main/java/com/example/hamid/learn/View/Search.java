package com.example.hamid.learn.View;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.hamid.learn.Adapters.Addapter;
import com.example.hamid.learn.Api.SearchApi;
import com.example.hamid.learn.Interfaces.Onmahsoolatrecive;
import com.example.hamid.learn.Model.post;
import com.example.hamid.learn.R;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

public class Search extends Activity {

    private ArrayList<String> searchadapter;
    private AutoCompleteTextView autosearch;
    private  ArrayAdapter<String> mainadapter_search;
    private RecyclerView recyclerView;
    private SearchApi api;


    public static Addapter mainsearchadapter;
    public RotateLoading rotateLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mainsearchadapter=new Addapter(Search.this);
        rotateLoading=(RotateLoading)findViewById(R.id.rotateloading);

        recyclerView=(RecyclerView)findViewById(R.id.recycleview_search);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL ));
        recyclerView.setAdapter(mainsearchadapter);
        api=new SearchApi();


        searchadapter=new ArrayList<>();

        mainadapter_search = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, searchadapter);

        autosearch = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView2);
        autosearch.setThreshold(1);//will start working from first character
        autosearch.setAdapter(mainadapter_search);//setting the adapter data into the AutoCompleteTextView
        autosearch.setTextColor(Color.BLACK);

        autosearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                api.getmahsoolat(new Onmahsoolatrecive() {
                    @Override
                    public void onrecive(List<post> posts) {
                        if (posts.size()>0) {
                            mainadapter_search.clear();
                            for (int i = 0; i < posts.size(); i++) {
                                mainadapter_search.add(posts.get(i).getTextView());
                            }
                            mainsearchadapter.addposts(posts);
                            mainadapter_search.notifyDataSetChanged();
                        }
                        if (autosearch.getText().toString().length()<1){
                            mainsearchadapter.clear();
                        }
                    }
                },MainActivity.daste,autosearch.getText().toString(),MainActivity.ostan);


            }
        });

        autosearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

            }
        });



    }
}
