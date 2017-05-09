package com.gkpoter.maptt.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.gkpoter.maptt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassFragment extends Fragment {

    private GridView gridView,g1,g2,g3;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_class, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        gridView = (GridView) getView().findViewById(R.id.gradView);
        g1 = (GridView) getView().findViewById(R.id.gradView1);
        g2 = (GridView) getView().findViewById(R.id.gradView2);
        g3 = (GridView) getView().findViewById(R.id.gradView3);
        data_list = new ArrayList<>();
        getData();
        String [] from ={"image","text"};
        int [] to = {R.id.image,R.id.text};
        sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.gradview_item, from, to);
        gridView.setAdapter(sim_adapter);
        g1.setAdapter(sim_adapter);
        g2.setAdapter(sim_adapter);
        g3.setAdapter(sim_adapter);

    }

    public List<Map<String, Object>> getData(){
        for(int i=0;i<10;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", R.mipmap.ic_launcher);
            map.put("text", "课程");
            data_list.add(map);
        }
        return data_list;
    }
}
