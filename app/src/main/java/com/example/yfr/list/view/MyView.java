package com.example.yfr.list.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import static android.graphics.Paint.Style.FILL_AND_STROKE;
import static android.graphics.Paint.Style.STROKE;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 上午10:18 2018/12/24
 * @Modified_By:
 */
public class MyView extends View {

    private Paint paint=new Paint();
    private Path path=new Path();
    public MyView(Context context) {
        super(context);
        init();
    }

    private void init() {

    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
//            canvas.drawCircle(400,400,300,paint);
//            canvas.drawRect(500,800,300,400,paint);
//            canvas.drawRoundRect(100, 100, 500, 300, 10, 50, paint);

//        path.addCircle(500,500,200,Path.Direction.CW);
//        path.addCircle(700,500,200,Path.Direction.CW);
//        canvas.drawPath(path,paint);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(STROKE);
        paint.setStrokeWidth(30);
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(200,200,100,paint);

//        path.addArc(100,100,300,300,-90,90);
//        paint.setColor(Color.RED);
//        canvas.drawPath(path,paint);
//
//        path.addArc(100,100,300,300,0,50);
//        paint.setColor(Color.GREEN);
//        canvas.drawPath(path,paint);

        RectF rectF=new RectF(100,100,300,300);
        paint.setColor(Color.RED);
        canvas.drawArc(rectF,-90,90,false,paint);

        paint.setColor(Color.GREEN);
        canvas.drawArc(rectF,0,50,false,paint);
//        Bitmap image = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        }
}
