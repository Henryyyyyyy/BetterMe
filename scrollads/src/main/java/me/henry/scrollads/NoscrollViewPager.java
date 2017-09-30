package me.henry.scrollads;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoscrollViewPager extends ViewPager {

    public NoscrollViewPager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public NoscrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return false;
    }
}