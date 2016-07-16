package com.weibo.customviewdemo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;

import com.weibo.customviewdemo.R;
import com.weibo.customviewdemo.view.CouponView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CouponView couponView;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);

        couponView = (CouponView) findViewById(R.id.coupon);
        couponView.setOnClickListener(this);

        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
        getWindow().setExitTransition(transition);
        getWindow().setReenterTransition(transition);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }
}
