package com.weibo.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/6/19.
 */
public class demo extends View {

    private Paint paint;

    public demo(Context context) {
        super(context);
    }

    public demo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public demo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        canvas.drawRect(0,0,400,400,paint);
        canvas.restore();
        paint.reset();
        canvas.clipRect(0,0,300,300, Region.Op.XOR);
    }
}
