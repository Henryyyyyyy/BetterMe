package me.henry.betterme.betterme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.model.MeiZhiJsonData;
import me.henry.betterme.betterme.view.RatioImageView;
import me.henry.betterme.betterme.view.Recycler.version1.BaseRvAdapter;

/**
 * Created by zj on 2017/4/5.
 * me.henry.betterme.betterme.adapter
 */

public class MeiZhiAdapter extends BaseRvAdapter<MeiZhiJsonData.MeiZhi>{
    public MeiZhiAdapter(Context mContext, List<MeiZhiJsonData.MeiZhi> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
    }

    @Override
    protected RecyclerView.ViewHolder setViewHolder(View itemView, int viewType) {
        return new MeiZhiHolder(itemView);

    }

    @Override
    protected void onBindData(RecyclerView.ViewHolder holder, int position) {
        MeiZhiHolder meizhiHolder=(MeiZhiHolder)holder;
        Glide.with(mContext).load(mDatas.get(position).getUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(meizhiHolder.ivMeiZhi);

    }
    class MeiZhiHolder extends RecyclerView.ViewHolder {
        RatioImageView ivMeiZhi;
        public MeiZhiHolder(View itemView) {
            super(itemView);
            ivMeiZhi = (RatioImageView) itemView.findViewById(R.id.ivMeiZhi);
            ivMeiZhi.setImageScale(1.0f);


        }
    }
}
