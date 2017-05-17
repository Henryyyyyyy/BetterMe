package me.henry.betterme.betterme.demo.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.view.Recycler.version2.AdapterDelegate;
import me.henry.betterme.betterme.view.Recycler.version2.BaseHolder;

import static me.henry.betterme.betterme.R.id.tvTestItem;

/**
 * Created by zj on 2017/3/28.
 * me.henry.betterme.betterme.view.Recycler.version2
 */

public class SecondDelegate extends AdapterDelegate<List<TestData>> {
    public SecondDelegate(Context context) {
        super(context);
    }
    @Override
    protected boolean isForViewType(@NonNull List<TestData> items, int position) {
        if (items.get(position).type==1){
            return true;
        }else {
            return false;
        }
    }
    @Override
    protected int getItemViewLayoutId() {
        return R.layout.item_test_right;
    }
    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent) {
        return new SecondHolder(mInflater.inflate(R.layout.item_test_right, parent, false));
    }
    @Override
    protected void onBindViewHolder(List<TestData> items, int position,BaseHolder holder, List<Object> payloads) {
        SecondHolder vh = (SecondHolder) holder;
        vh.name.setText(items.get(position).rightName);
    }
    static class SecondHolder extends BaseHolder {
        public TextView name;
        public SecondHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            existTakeNoneAdd(R.id.tvTestItem);
        }
    }
}
