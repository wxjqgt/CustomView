package com.weibo.customviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.weibo.customviewdemo.R;

/**
 * Created by Administrator on 2016/7/16.
 */
public class CouponView extends LinearLayout {

    private Paint paint;

    //小圆的个数
    private int circleNum;

    //圆间距
    private int gra = 8;

    //圆半径，可从外部设置
    private int radius = 10;

    //剩余空间
    private float remain;

    public CouponView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CouponView);
        if (typedArray.length() != 0){
            radius = typedArray.getInteger(R.styleable.CouponView_smallCircleRadius,10);
            gra = typedArray.getInteger(R.styleable.CouponView_smallCircleGra,8);
        }
    }

    public void setRadius(int radius) {
        if (radius != 0) {
            this.radius = radius;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0){
            remain = (w - gra) % (radius * 2 + gra);
        }
        circleNum = (w - gra) / (radius * 2 + gra);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0;i < circleNum;i++){
            //remain/2表示控制两头宽度一样，避免一边过大
            float x = gra + radius + remain / 2 + (gra + radius * 2) * i;
            //画下面的小圆
            canvas.drawCircle(x,0,radius,paint);
            //画上面的小圆
            canvas.drawCircle(x,getHeight(),radius,paint);
        }
    }
}
