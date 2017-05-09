package com.gkpoter.maptt.ui.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.gkpoter.maptt.R;
import com.gkpoter.maptt.ui.VideoActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class HomeFragment extends Fragment {

    private GridView gridView,gridView_top;
    private List<Map<String, Object>> data,topTitle;
    private SimpleAdapter adapter,adapter_title;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();

        data = new ArrayList<>();
        topTitle = new ArrayList<>();
        getData();
        String [] from ={"text"},top_from = {"image","title"};
        int [] to = {R.id.text1},top_to = {R.id.image_home_top,R.id.text_home_top};
        adapter = new SimpleAdapter(getActivity(), data, R.layout.gridview_item_home, from, to);
        adapter_title = new SimpleAdapter(getActivity(), topTitle, R.layout.gridview_item_home_top, top_from, top_to);
        gridView.setAdapter(adapter);
        gridView_top.setAdapter(adapter_title);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
    
    private void init() {
        gridView = (GridView) getView().findViewById(R.id.home_gridView);
        gridView_top = (GridView) getView().findViewById(R.id.home_gridView_top);
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.home_gridView_refresh);
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(0x19,0xb4,0xff));

    }

    @OnItemClick(R.id.home_gridView)
    public void gridViewItemClick(AdapterView<?> adapterView, View view, int i, long l){
        startActivity(new Intent(getActivity(), VideoActivity.class), ActivityOptions
                .makeSceneTransitionAnimation(getActivity(),view,"home_grid")
                .toBundle());
    }

    public List<Map<String, Object>> getData(){
        for(int i=0;i<4;i++){
            Map<String, Object> map = new HashMap<>();
            map.put("image", R.mipmap.ic_launcher);
            map.put("title", "课程");
            topTitle.add(map);
        }
        for(int i=0;i<20;i++){
            Map<String, Object> map = new HashMap<>();
            map.put("text", "课程");
            data.add(map);
        }
        return data;
    }
}
