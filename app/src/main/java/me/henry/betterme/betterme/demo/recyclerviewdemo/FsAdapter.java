package me.henry.betterme.betterme.demo.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import me.henry.betterme.betterme.view.Recycler.version2.BaseMutiTypeAdapter;

/**
 * Created by zj on 2017/3/28.
 * me.henry.betterme.betterme.view.Recycler.version2
 */

public class FsAdapter extends BaseMutiTypeAdapter<TestData> {
    private FirstDelegate mFirstDelegate;
    private SecondDelegate mSecondDelegate;
    public FsAdapter(Context context, List<TestData> datas) {
        super(context, datas);
    }

    @Override
    protected boolean isEnabledItem(int viewType) {
        return true;
    }

    @Override
    public void initDelegates() {
        mFirstDelegate=new FirstDelegate(mContext);
        mSecondDelegate=new SecondDelegate(mContext);
        mDelegateManager.addDelegate(mFirstDelegate);
        mDelegateManager.addDelegate(mSecondDelegate);
    }

}
