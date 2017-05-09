package com.gkpoter.maptt.ui.fragment_video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gkpoter.maptt.R;
import com.gkpoter.maptt.interface_.HttpCallbackListener;
import com.gkpoter.maptt.util.HttpUtils;
import com.gkpoter.maptt.util.L;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @BindView(R.id.other_button)
    Button button;
    @BindView(R.id.other_tv)
    TextView textView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.other_button)
    public void ButtonClick(View view){
        HashMap<String,String>map=new HashMap<>();
        map.put("1","2");
        HttpUtils.post("http://www.imooc.com/",map, new HttpCallbackListener.StringCallBack() {
            @Override
            public void OnRequest(final String response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(response);
                    }
                });
            }

            @Override
            public void OnFailure(Exception e) {
                L.e("error",e.getLocalizedMessage());
            }
        });
    }
}
