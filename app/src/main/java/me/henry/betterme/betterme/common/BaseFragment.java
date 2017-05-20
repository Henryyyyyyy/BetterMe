package me.henry.betterme.betterme.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by zj on 2017/3/30.
 * me.henry.betterme.betterme.common
 */

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {

    protected Unbinder unbinder;
    public T mPresenter;
    protected View mRootView;
    protected Context mContext;
    protected boolean isVisible; //是否可见
    public boolean isPrepared = false;// 标志位，标志Fragment已经初始化完成。
    private boolean hasLoaded = false;//标记已加载完成，保证懒加载只能加载一次

    public BaseFragment(Context Context) {
        this.mContext = Context;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(mRootView);
        mPresenter = initPresenter();
        mPresenter.attach((V) this);
        ButterKnife.bind(this,mRootView);
        initViewEventDataInCreate();
        isPrepared = true;
        prepareLoad();
        return mRootView;
    }

    /**
     * 实现Fragment数据的缓加载
     *
     * @param isVisibleToUser
     *
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }

    protected void onInVisible() {

    }
    //加载数据
    protected void onVisible() {
        onPageVisible(hasLoaded);//在下一次fragment可见的情况下调用
        prepareLoad();
    }

    protected void prepareLoad() {
        Log.d("kaca","isPrepared="+isPrepared+".......isVisible="+isVisible+"......hasLoaded="+hasLoaded);
        if (!isPrepared || !isVisible||hasLoaded) {return;}
        firstLoadData();
        hasLoaded = true;//注：关键步骤，确保数据只加载一次
    }
    protected abstract void firstLoadData();

    @Override
    public void onResume() {
        super.onResume();
        inResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hasLoaded = false;
        Log.d("haha","onDestroyView");
        unbinder.unbind();
        mPresenter.dettach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract T initPresenter();
    protected abstract int getLayout();
    protected abstract void initViewEventDataInCreate();

    /**
     * 用于在onresume拓展
     */
    protected void inResume() {}

    protected void onPageVisible(boolean isFirst) {

    }
}
