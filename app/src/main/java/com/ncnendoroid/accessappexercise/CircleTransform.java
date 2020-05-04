package com.ncnendoroid.accessappexercise;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.squareup.picasso.Transformation;

public class CircleTransform implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap zoomBitmp = BitmapUtils.zoom(source,300,300);
        Bitmap bitmap = BitmapUtils.circleBitmap(zoomBitmp);
        source.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "";
    }
}

class BitmapUtils {
    public static Bitmap circleBitmap(Bitmap source){
        int width = source.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return bitmap;
    }
    public static Bitmap zoom(Bitmap source,float width,float height){
        Matrix matrix = new Matrix();
        float scaleX = width / source.getWidth();
        float scaleY = height / source.getHeight();
        matrix.postScale(scaleX, scaleY);

        Bitmap bitmap = Bitmap.createBitmap(source,0,0,source.getWidth(),source.getHeight(),matrix,true);
        return bitmap;
    }
}