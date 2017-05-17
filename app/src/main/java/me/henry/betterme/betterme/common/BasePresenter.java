package me.henry.betterme.betterme.common;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by codeest on 2016/8/2.
 * Presenter基类
 */
public abstract class BasePresenter<T> {

private static final String TAG="BasePresenter";
    protected WeakReference<T> mViewRef; // view 的弱引用
    protected T mView;
    public Context mContext;
public BasePresenter(Context context){
    mContext=context;
}
    public void attach(T view) {
        mViewRef = new WeakReference<T>(view);
        mView = mViewRef.get();

    }

    public void dettach() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
            mView = null;

        }

    }



}
