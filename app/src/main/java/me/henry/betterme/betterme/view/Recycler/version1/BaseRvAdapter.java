package me.henry.betterme.betterme.view.Recycler.version1;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zj on 2017/3/24.
 * me.henry.betterme.betterme.view.Recycler
 */

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    protected List<T> mDatas;
    protected Context mContext;
    protected int itemLayoutId;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public int mHeaderID = 0;


    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;

    }

    public BaseRvAdapter(Context mContext, List<T> mDatas, int itemLayoutId) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderID != 0 && viewType == TYPE_HEADER) {
            View headView = LayoutInflater.from(mContext).inflate(mHeaderID, parent, false);
            return  setHeadHolder(headView, viewType);
        } else {
            View itemView = LayoutInflater.from(mContext).inflate(itemLayoutId, parent, false);
            return setViewHolder(itemView, viewType);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        onBindData(holder, getRealPosition(holder));

    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderID == 0 ? position : position - 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderID == 0) {
            return TYPE_NORMAL;
        } else {
            if (position == 0) {
                return TYPE_HEADER;
            } else {
                return TYPE_NORMAL;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderID == 0 ? mDatas.size() : mDatas.size() + 1;
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(view, (int) view.getTag());
        }
        return false;
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
     * 倒序插入
     *
     * @param datas
     */
    public void addDataSetReverse(List<T> datas) {
        for (int i = datas.size() - 1; i >= 0; i--) {
            mDatas.add(0, datas.get(i));
        }
        notifyDataSetChanged();
    }

    public void addDataSet(List<T> datas) {
        for (int i = 0; i < datas.size(); i++) {
            this.addItem(datas.get(i));
        }
        notifyDataSetChanged();
    }

    public void addHeader(int headId) {
        mHeaderID =headId;
        notifyItemInserted(0);
    }

    protected abstract RecyclerView.ViewHolder setViewHolder(View itemView, int viewType);
    protected  RecyclerView.ViewHolder setHeadHolder(View itemView, int viewType){
        return null;
    }

    protected abstract void onBindData(RecyclerView.ViewHolder holder, int position);
    /**
     * 处理GridLayoutManager的头部
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 处理StaggeredGridLayoutManager的头部
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if(lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            if (holder.getItemViewType()==TYPE_HEADER){
                p.setFullSpan(holder.getLayoutPosition() == 0);
            }
        }
    }
}
