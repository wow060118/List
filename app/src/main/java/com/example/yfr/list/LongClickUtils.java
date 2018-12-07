package com.example.yfr.list;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class LongClickUtils {

    public static void setLongClick(final Handler handler, final View longClickView, final long delayMillis, final OnLongClickListener longClickListener) {
        longClickView.setOnTouchListener(new OnTouchListener() {
            private int TOUCH_MAX = 50;
            private int mLastMotionX;
            private int mLastMotionY;
            Long l = System.currentTimeMillis();

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
//                        System.out.println("ACTION_UP");
                        handler.removeCallbacks(r);
//                        if (System.currentTimeMillis() - l > delayMillis) {
//                            if (longClickListener != null) {
//                                longClickListener.onLongClick(longClickView);
//                            }
//                            System.out.println("long click");
//                        }
//                        longClickView.invalidate();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Math.abs(mLastMotionX - x) > TOUCH_MAX
                                || Math.abs(mLastMotionY - y) > TOUCH_MAX) {
//                            System.out.println("ACTION_MOVE");
                            handler.removeCallbacks(r);
                        }
                        break;
                    case MotionEvent.ACTION_DOWN:
//                        System.out.println("ACTION_DOWN");
                        handler.removeCallbacks(r);
                        mLastMotionX = x;
                        mLastMotionY = y;
//                        l = System.currentTimeMillis();
                        handler.postDelayed(r, delayMillis);
                        break;
                }
                return false;
            }

            private Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (longClickListener != null) {
                        longClickListener.onLongClick(longClickView);
                    }
                }
            };
        });
    }

}
