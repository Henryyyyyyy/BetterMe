package me.henry.betterme.betterme.view.Recycler.version2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;



/**
 * Created by zj on 2017/3/27.
 * me.henry.betterme.betterme.view.Recycler.version2
 */

public abstract class BaseMutiTypeAdapter<T> extends RecyclerView.Adapter<BaseHolder> {
    protected DelegateManager<List<T>> mDelegateManager;
    protected List<T> mDatas;
    protected Context mContext;

    public BaseMutiTypeAdapter(Context context, List<T> datas) {
        mDelegateManager = new DelegateManager<>();
        mContext = context;
        mDatas = datas;
        initDelegates();
    }

    @Override
    public int getItemViewType(int position) {
        return mDelegateManager.getItemViewType(mDatas, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseHolder holder = mDelegateManager.onCreateViewHolder(parent, viewType);
        setUpItemListener(parent, holder, viewType);
        setUpViewListener(parent, holder, viewType, holder.getViewsWhichNeedListener());
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        mDelegateManager.onBindViewHolder(mDatas, position, holder);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position, List payloads) {
        mDelegateManager.onBindViewHolder(mDatas, position, holder, payloads);
    }

    /**
     * 添加一个item
     *
     * @param data
     */
    public void addItem(T data) {
        mDatas.add(data);
        notifyDataSetChanged();

    }

    /**
     * 删除一个item
     *
     * @param position
     */
    public void removeItem(final int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 重新设置数据集
     *
     * @param data
     */
    public void setDatas(List<T> data) {
        mDatas = data;
        notifyDataSetChanged();

    }

    /**
     * 指定特定类型才可以设置item点击listener
     * 设置true才可以触发点击事件
     *
     * @param viewType
     * @return
     */
    protected abstract boolean isEnabledItem(int viewType);

    /**
     *
     * 设置item的点击事件，或者长按事件
     * @param parent
     * @param viewHolder
     * @param viewType
     */
    protected void setUpItemListener(final ViewGroup parent, final BaseHolder viewHolder, int viewType) {
        if (!isEnabledItem(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(v, viewHolder, position);
                }
            }
        });

//        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (mOnItemClickListener != null) {
//                    int position = viewHolder.getAdapterPosition();
//                    return mOnItemClickListener.onItemLongClick(v, viewHolder, position);
//                }
//                return false;
//            }
//        });
    }

    /**
     * 设置item中的某个view的点击事件，或者长按事件
     * @param parent
     * @param viewHolder
     * @param viewType
     * @param mViews
     */
    protected void setUpViewListener(final ViewGroup parent, final BaseHolder viewHolder, int viewType, SparseArray<View> mViews) {
        int layoutIdkey;
        if (mViews.size() > 0) {
            for (int i = 0; i < mViews.size(); i++) {
                layoutIdkey = mViews.keyAt(i);
                viewHolder.existTakeNoneAdd(layoutIdkey).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mOnViewClickListener != null) {
                            int position = viewHolder.getAdapterPosition();
                            mOnViewClickListener.onViewClick(view, viewHolder, position);
                        }
                    }
                });
//           viewHolder.existTakeNoneAdd(layoutIdkey).setOnLongClickListener(new View.OnLongClickListener() {
//               @Override
//               public boolean onLongClick(View view) {
//                   if (mOnViewClickListener != null) {
//                       int position = viewHolder.getAdapterPosition();
//                       return mOnViewClickListener.onViewLongClick(view, viewHolder, position);
//                   }
//                   return false;
//               }
//           });
            }
        }

    }

    /**
     * 初始化类型代理
     */
    public abstract void initDelegates();


    //以下是关于Item中的view的点击事件---------------------
    protected OnViewClickListener mOnViewClickListener;

    public interface OnViewClickListener {
        void onViewClick(View view, BaseHolder holder, int position);
        //   boolean onViewLongClick(View view, BaseHolder holder, int position);
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        this.mOnViewClickListener = onViewClickListener;
    }

    //以下是关于Item的点击事件---------------------
    protected OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, BaseHolder holder, int position);

        // boolean onItemLongClick(View view, BaseHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
