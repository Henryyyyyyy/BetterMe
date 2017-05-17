package me.henry.betterme.betterme.view.customRowView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import me.henry.betterme.betterme.view.customRowView.describer.BaseRowDescriber;

/**
 * Created by zj on 2017/5/16.
 * me.henry.betterme.betterme.view.customRowView
 */

public abstract class BaseRowView extends LinearLayout{
    public BaseRowView(Context context) {
        super(context);
    }

    public BaseRowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public abstract void initializeData(BaseRowDescriber describer);
    public abstract void notifyDataChange();
    public abstract void setOnRowClickListener(onRowClickListener listener);

}
