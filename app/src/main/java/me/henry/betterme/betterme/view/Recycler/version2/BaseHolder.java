package me.henry.betterme.betterme.view.Recycler.version2;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by zj on 2017/3/29.
 * me.henry.betterme.betterme.view.Recycler.version2
 */

public class BaseHolder extends RecyclerView.ViewHolder {
    public View mConvertView;
    public SparseArray<View> mViews;

    public BaseHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过viewId获取控件,没有存进去的话，默认存进去，用来做view的click事件
     * 有就拿，没就加
     * @param viewId
     * @return
     */
    public <T extends View> T existTakeNoneAdd(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public SparseArray<View> getViewsWhichNeedListener() {
        return mViews;
    }
}
