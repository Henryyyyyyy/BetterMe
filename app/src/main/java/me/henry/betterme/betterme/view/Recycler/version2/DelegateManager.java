package me.henry.betterme.betterme.view.Recycler.version2;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by zj on 2017/3/27.
 * me.henry.betterme.betterme.view.Recycler.version2
 * 类型代理管理器，有多少个类型的item，就有多少个viewtype（1234567），viewtype其实
 * 就是类型代理（delegate）的个数
 */
public class DelegateManager<T> {
    /**
     *
     * 作为空参数用
     */
    private static final List<Object> PAYLOADS_EMPTY_LIST = Collections.emptyList();
    /**
     * 用于存放item代理的map
     */
    protected SparseArrayCompat<AdapterDelegate<T>> delegates = new SparseArrayCompat();
    /**
     * 添加item代理
     */
    public DelegateManager<T> addDelegate( AdapterDelegate<T> delegate) {
        int viewType = delegates.size();//已经存在多少个item代理,添加在map的最末端位置
        return addDelegate(viewType, delegate);
    }

    /**
     * 添加item代理
     * 在特定位置加item代理
     */
    public DelegateManager<T> addDelegate(int viewType,  AdapterDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("AdapterDelegate is null!");
        }
        delegates.put(viewType, delegate);//在特定位置加item代理
        return this;
    }
    /**
     * 移除item代理
     * 在特定位置加item代理
     */
    public DelegateManager<T> removeDelegate( AdapterDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("AdapterDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);
        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }
    /**
     * 移除item代理
     * 在特定位置加item代理
     */
    public DelegateManager<T> removeDelegate(int viewType) {
        delegates.remove(viewType);
        return this;
    }

    /**
     *通过isForViewType判断delegatelist中是否含有该item的类型，如果有，就返回这个类型代理的位置（index）
     * @param items
     * @param position
     * @return
     */
    public int getItemViewType(T items, int position) {

        if (items == null) {
            throw new NullPointerException("Items datasource is null!");
        }

        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            AdapterDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(items, position)) {
                return delegates.keyAt(i);
            }
        }
        throw new NullPointerException(
                "No AdapterDelegate added that matches position=" + position + " in data source");
    }

    /**
     * 用于测试的?
     *
     * @param delegate
     * @return
     */
    public int getViewType(AdapterDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("Delegate is null");
        }
        int index = delegates.indexOfValue(delegate);
        if (index == -1) {
            return -1;
        }
        return delegates.keyAt(index);
    }

    /**
     * 通过viewType获取类型代理类
     * @param viewType
     * @return
     */
    public AdapterDelegate<T> getDelegateByViewType(int viewType) {
        AdapterDelegate<T> delegate = delegates.get(viewType);
        return delegate;
    }


     public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate<T> delegate = getDelegateByViewType(viewType);
        if (delegate == null) {
            throw new NullPointerException("No AdapterDelegate added for ViewType " + viewType);
        }
         BaseHolder vh = delegate.onCreateViewHolder(parent);
        if (vh == null) {
            throw new NullPointerException("ViewHolder returned from AdapterDelegate " + delegate + " for ViewType =" + viewType + " is null!");
        }
        return vh;
    }

    public void onBindViewHolder(T items, int position, BaseHolder viewHolder, List payloads) {
        AdapterDelegate<T> delegate = getDelegateByViewType(viewHolder.getItemViewType());
        if (delegate == null) {
            throw new NullPointerException("No delegate found for item at position = " + position + " for viewType = " + viewHolder.getItemViewType());
        }
        delegate.onBindViewHolder(items, position, viewHolder, payloads != null ? payloads : PAYLOADS_EMPTY_LIST);
    }


    public void onBindViewHolder(T items, int position, BaseHolder viewHolder) {
        onBindViewHolder(items, position, viewHolder, PAYLOADS_EMPTY_LIST);
    }
}
