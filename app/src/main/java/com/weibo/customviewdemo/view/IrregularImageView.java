package com.weibo.customviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.weibo.customviewdemo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 巴巴 on 2016/8/1.
 */

public class IrregularImageView extends ImageView {

    private float briefLength = 10, itemSpace = 5;
    private String url1, url2, url3;
    private Context context;

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));

        int h = getHeight();
        int w = getWidth();
        float hItem = (h - itemSpace * 2) / 3;

        getItem1(hItem, w, canvas, h);
    }

    public void setImageSrc(String url1, String url2, String url3) {
        this.url1 = url1;
        this.url2 = url2;
        this.url3 = url3;
    }

    private void getItem1(final float hItem, final int w, final Canvas canvas, final int h) {
        GetImageRequest(url2, context, new HandleBitmap() {
            @Override
            public void handleBitmao(Bitmap bitmap) {
                BitmapShader shader1 = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

                Paint paint1 = new Paint();
                paint1.setAntiAlias(true);
                paint1.setShader(shader1);

                //第一部分的图片左下角定点坐标y轴
                float leftY1 = hItem - briefLength;
                //第一部分的图片右下角定点坐标y轴
                float rightY1 = hItem + briefLength;

                Path path1 = new Path();
                path1.lineTo(w, 0);
                path1.lineTo(w, rightY1);
                path1.lineTo(0, leftY1);
                path1.close();
                canvas.drawPath(path1, paint1);
                paint1.reset();

                getItem2(hItem, leftY1, rightY1, w, h, canvas);
            }
        });
    }

    private void getItem2(final float hItem, final float leftY1, final float rightY1,
                          final int w, final int h, final Canvas canvas) {
        GetImageRequest(url2, context, new HandleBitmap() {
            @Override
            public void handleBitmao(Bitmap bitmap) {
                BitmapShader shader2 = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                Paint paint2 = new Paint();
                paint2.setShader(shader2);

                //第一部分的图片左下角定点坐标y轴
                float leftY2 = leftY1 + hItem + itemSpace;
                //第一部分的图片右下角定点坐标y轴
                float rightY2 = rightY1 * 2 + itemSpace;

                Path path2 = new Path();
                path2.moveTo(0, leftY1 + itemSpace);
                path2.lineTo(w, rightY1 + itemSpace);
                path2.lineTo(w, leftY2);
                path2.lineTo(0, rightY2);
                path2.close();
                canvas.drawPath(path2, paint2);
                paint2.reset();
                bitmap.recycle();
                getItem3(rightY2, leftY2, w, h, canvas);
            }
        });
    }

    private void getItem3(final float rightY2, final float leftY2,
                          final int w, final int h, final Canvas canvas) {
        GetImageRequest(url3, context, new HandleBitmap() {
            @Override
            public void handleBitmao(Bitmap bitmap) {
                if (bitmap != null) {
                    BitmapShader shader3 = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                    Paint paint3 = new Paint();
                    paint3.setShader(shader3);
                    Path path3 = new Path();
                    path3.moveTo(0, rightY2 + itemSpace);
                    path3.lineTo(w, leftY2 + itemSpace);
                    path3.lineTo(w, h);
                    path3.lineTo(0, h);
                    path3.close();
                    canvas.drawPath(path3, paint3);
                    paint3.reset();
                }
            }
        });

    }

    public interface HandleBitmap {
        void handleBitmao(Bitmap bitmap);
    }

    public static void GetImageRequest(final String url, Context context, final HandleBitmap handleBitmap) {
        if (!isNetworkAvailable(context)) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL u = new URL(url);
                    HttpURLConnection uRLConnection = (HttpURLConnection) u.openConnection();
                    InputStream is = uRLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    if (bitmap != null) {
                        handleBitmap.handleBitmao(bitmap);
                    }
                    is.close();
                    uRLConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static boolean isNetworkAvailable(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    //System.out.println(i + "===状态===" + networkInfo[i].getState());
                    //System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public IrregularImageView(Context context) {
        super(context);
        this.context = context;
    }

    public IrregularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.IrregularImageView);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.IrregularImageView_briefLength:
                    briefLength = array.getDimension(attr, 10);
                    break;
                case R.styleable.IrregularImageView_ItemSpace:
                    itemSpace = array.getDimension(attr, 10);
                    break;
            }

        }
        array.recycle();
    }

    public IrregularImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

}
