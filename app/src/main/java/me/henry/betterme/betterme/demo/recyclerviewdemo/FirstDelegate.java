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

/**
 * Created by zj on 2017/3/28.
 * me.henry.betterme.betterme.view.Recycler.version2
 */

public class FirstDelegate extends AdapterDelegate<List<TestData>> {
    public FirstDelegate(Context context) {
        super(context);
    }

    @Override
    protected boolean isForViewType(@NonNull List<TestData> items, int position) {
        if (items.get(position).type == 0) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    protected int getItemViewLayoutId() {
        return R.layout.item_test_first;
    }
    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent) {
        return new FirstHolder(mInflater.inflate(R.layout.item_test_first, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull List<TestData> items, int position, @NonNull BaseHolder holder, @NonNull List<Object> payloads) {
        FirstHolder vh = (FirstHolder) holder;
        vh.name.setText(items.get(position).leftName);
    }

    static class FirstHolder extends BaseHolder {
        public TextView name;
        public TextView tvTestItem;
        public FirstHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            tvTestItem = (TextView) itemView.findViewById(R.id.tvTestItem);
            existTakeNoneAdd(R.id.tvTestItem);
        }
    }
}
