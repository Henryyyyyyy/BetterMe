package me.henry.betterme.betterme.view.customRowView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.view.customRowView.describer.BaseRowDescriber;
import me.henry.betterme.betterme.view.customRowView.describer.RowDescriber;
import me.henry.betterme.betterme.view.customRowView.describer.RowProfileDescriber;

/**
 * Created by zj on 2017/5/16.
 * me.henry.betterme.betterme.view.customRowView
 */

public class GroupView extends LinearLayout {
    private Context mContext;
    private List<BaseRowDescriber> mRowDescribers = new ArrayList<>();
    private onRowClickListener mListener;

    public GroupView(Context context) {
        super(context);
        this.mContext = context;
        initializeView();

    }

    public GroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initializeView();

    }


    public GroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initializeView();
    }

    /**
     * 初始化groupview
     */
    private void initializeView() {
        setOrientation(VERTICAL);
        LayoutInflater.from(mContext).inflate(R.layout.widget_group_view, this);

    }

    /**
     * 要调用这个方法 group里面才会有row
     */
    public void notifyDataChange() {
        if (mRowDescribers != null && mRowDescribers.size() >                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    0) {
            BaseRowView row=null;
            BaseRowDescriber describer = null;
            for (int i = 0; i < mRowDescribers.size(); i++) {
                describer = mRowDescribers.get(i);
                if (describer instanceof RowDescriber) {
                    row = new RowView(mContext);
                }else if (describer instanceof RowProfileDescriber){
                    row = new ProfileRowView(mContext);
                }
                row.initializeData(describer);
                row.notifyDataChange();
                row.setOnRowClickListener(mListener);
                addView(row);
            }
        } else {
            setVisibility(GONE);
        }
    }

    /**
     * 设置监听器
     *
     * @param listener
     */
    public void setOnRowClickListener(onRowClickListener listener) {
        this.mListener = listener;
    }

    /**
     * 设置数据的方法之一，一个个discriber放进去
     *
     * @param describer
     */
    public void add(RowDescriber describer) {
        mRowDescribers.add(describer);
    }

    /**
     * 设置数据的方法之二，全部数据放进去
     *
     * @param describer
     */
    public void initializeData(ArrayList<BaseRowDescriber> describer) {
        this.mRowDescribers = describer;

    }
}
