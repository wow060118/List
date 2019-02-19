package com.example.yfr.demo;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午3:06 2019/2/18
 * @Modified_By:
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


import java.lang.ref.WeakReference;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 上午11:58 2019/2/18
 * @Modified_By:
 */
public class CircleProgressView extends View {
    private int mWidth, mHeight;
    private int mProgress = 0;
    private Paint paint = new Paint(ANTI_ALIAS_FLAG);
    private Paint paintText = new Paint(ANTI_ALIAS_FLAG);
    private Paint paintRoundRect = new Paint(ANTI_ALIAS_FLAG);
    private String defaultText = "0%";
    private int bgColor = Color.parseColor("#f3f3f3");
    private int cirColor = Color.parseColor("#3969f5");
    private int lineWidth = 30;
    private RectF oval = new RectF();
    //进度条
    private boolean isEnding = true;
    private int centerX;
    //paintRoundRect 的左右边界
    private float rectRight, rectLeft;
    //步长
    private int step = 0;
    //动画标记
    private boolean isloading = false;
    //开始结束标记
    private boolean download = false;

    private CircleProgressHandler circleProgressHandler;

    public CircleProgressView(Context context) {
        super(context);
        initView();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {

        paint.setStrokeWidth(lineWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paintText.setTextSize(50);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        switch (MeasureSpec.getMode(widthMeasureSpec)) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mWidth = getScreenWidth();
                break;
            case MeasureSpec.EXACTLY:
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
                if (mWidth < 0) {
                    mWidth = getScreenWidth();
                }
                break;
        }

        switch (MeasureSpec.getMode(heightMeasureSpec)) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mHeight = getScreenHeight();
                break;
            case MeasureSpec.EXACTLY:
                mHeight = MeasureSpec.getSize(heightMeasureSpec);
                if (mHeight < 0) {
                    mHeight = getScreenHeight();
                }
                break;
        }
        centerX = mWidth / 2;
//        rectLeft=centerX-(mHeight)/2;
        rectLeft = 0;
//        rectRight=centerX+(mHeight)/2;
        rectRight = mWidth;
        step = (mWidth / 2 - centerX - (mHeight) / 2) / 10;

        super.onMeasure(MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));
    }

    public void setSize(int width, int height) {
        mWidth = width;
        mHeight = height;
        requestLayout();
    }

    public void setProgress(int progress) {
        if (progress == mProgress) {
            return;
        }
        if (progress < 0) {
            progress = 0;
        }
        if (progress > 100) {
            progress = 100;
        }
        mProgress = progress;
        defaultText = progress + "%";
        invalidate();
        if (mProgress == 100) {
            isEnding = true;
        }

    }

    public void startDownLoad() {
        isloading = true;
        download = true;
        circleProgressHandler = new CircleProgressHandler(this);
        circleProgressHandler.sendEmptyMessage(0);
    }

    public void endDownLoad() {
        isloading = true;
        isEnding = true;
        download = false;
        circleProgressHandler = new CircleProgressHandler(this);
        circleProgressHandler.sendEmptyMessage(0);
    }

    public int getProgress() {
        return mProgress;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isEnding) {
            if (mHeight <= 0 || mWidth <= 0) {
                return;
            }

            paint.setColor(bgColor);
            canvas.drawCircle(mWidth / 2, mHeight / 2, (mHeight - lineWidth) / 2, paint);

            paint.setColor(cirColor);
            canvas.drawArc(centerX - (mHeight - lineWidth) / 2, lineWidth / 2, centerX + (mHeight - lineWidth) / 2, mHeight - lineWidth / 2, -90, 360 * mProgress / 100, false, paint);

            paintText.setColor(cirColor);
            paintText.setTextAlign(Paint.Align.CENTER);
            Paint.FontMetrics fm = paint.getFontMetrics();
            float textCenterY = mHeight / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
            float textCenterX = (float) mWidth / 2;
            Rect rect = new Rect();
            paintText.getTextBounds(defaultText, 0, defaultText.length(), rect);
            int offsety = (rect.top + rect.bottom) / 2;
            canvas.drawText(defaultText, textCenterX, textCenterY - offsety, paintText);
        } else {
            paintRoundRect.setColor(cirColor);
//            canvas.drawRoundRect(centerX-(mHeight-lineWidth)/2,0+lineWidth/2,centerX+(mHeight-lineWidth)/2,mHeight-lineWidth/2,mHeight / 2 ,mHeight / 2,paintRoundRect);
            canvas.drawRoundRect(rectLeft, 0, rectRight, mHeight, mHeight / 2, mHeight / 2, paintRoundRect);
            Log.e("canvas", "left:" + rectLeft + "   right:" + rectRight);
        }

    }

    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private int getScreenHeight() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    static class CircleProgressHandler extends Handler {

        private WeakReference<CircleProgressView> weakReference;

        public CircleProgressHandler(CircleProgressView circleProgressView) {
            weakReference = new WeakReference<>(circleProgressView);
        }

        @Override
        public void dispatchMessage(Message msg) {
            CircleProgressView circleProgressView = weakReference.get();
            if (circleProgressView != null && circleProgressView.isloading) {

                float drawPositiveleft = circleProgressView.rectLeft;
                float drawPositiveRight = circleProgressView.rectRight;
                if (!circleProgressView.download ) {
                    drawPositiveleft = drawPositiveleft + circleProgressView.step;
                    drawPositiveRight = drawPositiveRight - circleProgressView.step;
                    if (drawPositiveleft <= 0) {
                        circleProgressView.isloading = false;
                        drawPositiveleft = 0;
                        drawPositiveRight = circleProgressView.mWidth;
                    }
                } else  {
                    drawPositiveleft = drawPositiveleft - circleProgressView.step;
                    drawPositiveRight = drawPositiveRight + circleProgressView.step;
                    if (drawPositiveleft >= circleProgressView.centerX - (circleProgressView.mHeight) / 2) {
                        circleProgressView.isloading = false;
                        circleProgressView.isEnding = false;
                        drawPositiveleft = circleProgressView.centerX - (circleProgressView.mHeight) / 2;
                        drawPositiveRight = circleProgressView.centerX + (circleProgressView.mHeight) / 2;
                    }
                }
                circleProgressView.rectLeft = drawPositiveleft;
                circleProgressView.rectRight = drawPositiveRight;
                circleProgressView.postInvalidate();
                sendEmptyMessageDelayed(0, 5);

            }

        }
    }
}
