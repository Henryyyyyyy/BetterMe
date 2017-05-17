package me.henry.betterme.betterme.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by zj on 2017/3/30.
 * me.henry.betterme.betterme.view
 */

public class OptimizeViewpager extends ViewPager {
    private int preX = 0;
    private int preY = 0;

    public OptimizeViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OptimizeViewpager(Context context) {
        super(context);
    }

}