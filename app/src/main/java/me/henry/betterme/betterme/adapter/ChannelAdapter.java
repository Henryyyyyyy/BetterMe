package me.henry.betterme.betterme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.model.Channel;
import me.henry.betterme.betterme.view.Recycler.version1.BaseRvAdapter;


/**
 * Created by zj on 2017/7/20.
 * me.henry.betterme.betterme.adapter
 */

public class ChannelAdapter extends BaseRvAdapter<Channel>{
    public ChannelAdapter(Context mContext, List<Channel> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
    }

    @Override
    protected RecyclerView.ViewHolder setViewHolder(View itemView, int viewType) {
        return new ChannelHolder(itemView);
    }

    @Override
    protected void onBindData(RecyclerView.ViewHolder holder, int position) {

    }
    class ChannelHolder extends RecyclerView.ViewHolder {
        ImageView ivChannelCover;
        TextView tvChannelName;
        RelativeLayout rlContent;
        public ChannelHolder(View itemView) {
            super(itemView);
            ivChannelCover= (ImageView) itemView.findViewById(R.id.ivChannelCover);
            tvChannelName= (TextView) itemView.findViewById(R.id.tvChannelName);
            rlContent= (RelativeLayout) itemView.findViewById(R.id.rlContent);
        }

    }
}
