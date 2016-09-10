package com.weibo.customviewdemo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;

import com.weibo.customviewdemo.R;
import com.weibo.customviewdemo.fragment.BlankFragment;
import com.weibo.customviewdemo.view.CouponView;
import com.weibo.customviewdemo.view.TabPageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabPageIndicator indicator;
    private ViewPager viewPager;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_main);

        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        getWindow().setExitTransition(transition);
        getWindow().setReenterTransition(transition);

        indicator = findView(R.id.indicator);
        viewPager = findView(R.id.viewpager);

        List<Fragment> list = new ArrayList<>();
        List<String> title = new ArrayList<>();
        for (int i = 0;i < 5;i++){
            list.add(BlankFragment.newInstance("这是第" + i + "页"));
            title.add("这是第" + i + "页");
        }
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),list,title));

        indicator.setViewPager(viewPager);
        indicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);
        indicator.setTextColorSelected(Color.RED);
        indicator.setIndicatorColor(Color.RED);
        indicator.setIndicatorHeight(10);
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;
        private List<String> title;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> list,List<String> title) {
            super(fm);
            this.list = list;
            this.title = title;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

    }

    private <T extends View> T findView(int id){
        return (T) findViewById(id);
    }

}
