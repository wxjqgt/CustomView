package com.weibo.customviewdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weibo.customviewdemo.R;

public class BlankFragment extends Fragment {

    private static final String TEXT = "text";
    private String text;

    private View view;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        textView = findView(R.id.textview);
        textView.setText(text);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null){
            text = bundle.getString(TEXT);
        }
    }

    public BlankFragment() {}

    public static BlankFragment newInstance(String text) {
        BlankFragment fragment = new BlankFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT,text);
        fragment.setArguments(bundle);
        return fragment;
    }

    private <T extends View> T findView(int id){
        return (T) view.findViewById(id);
    }

}
