package me.henry.betterme.betterme.view.Recycler.version1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.model.Person;
import me.henry.betterme.betterme.view.Recycler.version1.BaseRvAdapter;

/**
 * Created by zj on 2017/3/24.
 * me.henry.betterme.betterme.view.Recycler
 *
 */
public class TestAdapter extends BaseRvAdapter<Person> {
    public TestAdapter(Context mContext, List<Person> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
    }
    @Override
    protected RecyclerView.ViewHolder setViewHolder(View itemView,int viewType) {
        return new TestHolder(itemView);
    }

    @Override
    protected RecyclerView.ViewHolder setHeadHolder(View itemView, int viewType) {
        return new HeadHolder(itemView);
    }

    @Override
    protected void onBindData(RecyclerView.ViewHolder holder, int position) {
        TestHolder testHolder=(TestHolder)holder;
        testHolder.tvName.setText(mDatas.get(position).getName());
        testHolder.tvWhat.setText(mDatas.get(position).getAge()+"");
        testHolder.tvWhat.setTag(position);
        testHolder.tvWhat.setOnClickListener(this);
    }
     class TestHolder extends RecyclerView.ViewHolder {
         TextView tvName;
         TextView tvWhat;
         public TestHolder(View itemView) {
             super(itemView);
             tvName = (TextView) itemView.findViewById(R.id.tvName);
             tvWhat = (TextView) itemView.findViewById(R.id.tvWhat);

         }
     }
    class HeadHolder extends RecyclerView.ViewHolder {
        public HeadHolder(View itemView) {
            super(itemView);
        }
    }
}
