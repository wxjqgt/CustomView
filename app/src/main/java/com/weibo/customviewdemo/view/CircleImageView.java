package com.weibo.customviewdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.weibo.customviewdemo.R;

/**
 * Created by Administrator on 2016/6/26.
 */
public class CircleImageView extends ImageView {

    private Paint paint;
    private int width = 0,color = Color.WHITE;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBorderWidth(int width) {
        if (width > 0) {
            this.width = width;
        }
    }

    public void setBorderColor(int color) {
        this.color = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint = new Paint();
        paint.setAntiAlias(true);

        Drawable drawable = getDrawable();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        int r1 = Math.min(getWidth(),getHeight());
        Bitmap b = getCroppedBitmap(bitmap,r1);

        BitmapShader shader = new BitmapShader(b, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);

        int r = Math.min(getWidth(),getHeight()) / 2;

        if (width > 0) {
            Paint p = new Paint();
            p.setColor(color);
            p.setAntiAlias(true);
            canvas.drawCircle(r, r, r, p);
            canvas.save();
            p.reset();

            canvas.drawCircle(r, r, r - width, paint);
            canvas.restore();
            paint.reset();
        }else {
            canvas.drawCircle(r, r, r, paint);
            paint.reset();
        }

    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp = null;
        if(bmp.getWidth() != radius || bmp.getHeight() != radius){
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        } else{
            sbmp = bmp;
        }
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(sbmp.getWidth() / 2+0.7f, sbmp.getHeight() / 2+0.7f,
                sbmp.getWidth() / 2+0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }

}










