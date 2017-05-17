package me.henry.betterme.betterme.view.customRowView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.view.customRowView.describer.GroupDescriber;

/**
 * Created by zj on 2017/5/16.
 * me.henry.betterme.betterme.view.customRowView
 */

public class ContainerView extends LinearLayout {
    private Context mContext;
    private onRowClickListener mListener;
    private ArrayList<GroupDescriber> mGroupDescribers =new ArrayList<>();

    public ContainerView(Context context) {
        super(context);
        mContext=context;
        initialzeView();
    }
    public ContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initialzeView();
    }

    public ContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initialzeView();
    }

    private void initialzeView() {
        setOrientation(VERTICAL);
        LayoutInflater.from(mContext).inflate(R.layout.widget_group_view, this);
        setBackgroundResource(android.R.color.transparent);
    }
    /**
     * 要调用这个方法 group里面才会有row
     */
    public void notifyDataChange(){
        if (mGroupDescribers !=null&& mGroupDescribers.size()>0){
            GroupView group;
            float density=mContext.getResources().getDisplayMetrics().density;
            LayoutParams param=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            param.topMargin= (int) (50*density);
            for (GroupDescriber describer: mGroupDescribers){
                group=new GroupView(mContext);
                group.initializeData(describer.rowDescribers);
                group.setOnRowClickListener(mListener);
                group.notifyDataChange();
                addView(group,param);
            }
            setVisibility(VISIBLE);
        }else {
            setVisibility(GONE);
        }
    }
    public void setOnRowClickListener(onRowClickListener listener) {
        mListener = listener;
    }
    /**
     * 设置数据的方法之一，一个个discriber放进去
     * @param describer
     */
    public void add(GroupDescriber describer){
        mGroupDescribers.add(describer);
    }

    /**
     * 设置数据的方法之二，全部数据放进去
     * @param describers
     */
    public void initializeData(ArrayList<GroupDescriber> describers) {
        mGroupDescribers =describers;
    }
}
