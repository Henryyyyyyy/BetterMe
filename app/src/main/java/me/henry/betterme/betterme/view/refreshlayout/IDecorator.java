package me.henry.betterme.betterme.view.refreshlayout;

import android.view.MotionEvent;

/**
 * Created by lcodecore on 2017/3/1.
 */

public interface IDecorator {
    boolean interceptTouchEvent(MotionEvent ev);

    boolean dealTouchEvent(MotionEvent ev);

    void onFingerDown(MotionEvent ev);

    void onFingerScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY, float velocityY);

    void onFingerFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
}
