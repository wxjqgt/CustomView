package com.weibo.customviewdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.weibo.customviewdemo.R;
import com.weibo.customviewdemo.util.AddCartAnimation;
import com.weibo.customviewdemo.view.recyclerView.CommonAdapter;
import com.weibo.customviewdemo.view.recyclerView.OnRecyclerViewItemClickListener;
import com.weibo.customviewdemo.view.recyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Main2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        List<Integer> datas = new ArrayList<>();
        for (int i = 0; i < 20;i++){datas.add(R.mipmap.ic_launcher);}
        CommonAdapter<Integer> commonAdapter = new CommonAdapter<Integer>(this,R.layout.re_item,datas) {
            @Override
            public void convert(ViewHolder holder, Integer s, int position) {
                holder.setImageViewSrc(R.id.iv_item,s);
            }
        };
        recyclerView.setAdapter(commonAdapter);
        recyclerView.addOnItemTouchListener(new OnRecyclerViewItemClickListener(recyclerView) {
            @Override
            public void OnItemClickLitener(RecyclerView.ViewHolder viewHolder) {
                ImageView startView = (ImageView) viewHolder.itemView.findViewById(R.id.iv_item);
                AddCartAnimation.AddToCart(startView,imageView,Main2Activity.this,relativeLayout,1);
            }

            @Override
            public void OnItemLongClickLitener(RecyclerView.ViewHolder viewHolder) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @BindView(R.id.relative)
    RelativeLayout relativeLayout;
    @BindView(R.id.re)
    RecyclerView recyclerView;
    @BindView(R.id.ivend)
    ImageView imageView;

}
