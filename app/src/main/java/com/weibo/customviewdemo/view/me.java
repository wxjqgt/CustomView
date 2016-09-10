package com.weibo.customviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 巴巴 on 2016/8/4.
 */

public class me extends View {

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        canvas.drawArc(new RectF(0,0,500,500),-90,90,true,paint);
    }

    public me(Context context) {
        super(context);
    }

    public me(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public me(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
