package me.henry.betterme.betterme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.model.MusicInfo;
import me.henry.betterme.betterme.utils.MusicUtil;
import me.henry.betterme.betterme.view.Recycler.version1.BaseRvAdapter;

/**
 * Created by zj on 2017/4/7.
 * me.henry.betterme.betterme.adapter
 */

public class MusicAdapter extends BaseRvAdapter<MusicInfo>{


    public MusicAdapter(Context mContext, List<MusicInfo> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
    }
    @Override
    protected RecyclerView.ViewHolder setViewHolder(View itemView, int viewType) {
        return new MusicHolder(itemView);
    }
    @Override
    protected void onBindData(RecyclerView.ViewHolder holder, int position) {
       MusicHolder musicHolder=(MusicHolder)holder;
        musicHolder.tvName.setText(mDatas.get(position).musicName);
        musicHolder.tvSinger.setText(mDatas.get(position).artist);
        musicHolder.rlContent.setTag(position);
        musicHolder.rlContent.setOnClickListener(this);

        Glide.with(mContext)
             .load(MusicUtil.getAlbumArtUri(mDatas.get(position).albumId))
             .placeholder(R.drawable.stayaway)
             .into(musicHolder.ivCover);

    }

    class MusicHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvName;
        TextView tvSinger;
        RelativeLayout rlContent;
        public MusicHolder(View itemView) {
            super(itemView);
            ivCover= (ImageView) itemView.findViewById(R.id.ivCover);
            tvName= (TextView) itemView.findViewById(R.id.tvName);
            tvSinger= (TextView) itemView.findViewById(R.id.tvSinger);
            rlContent= (RelativeLayout) itemView.findViewById(R.id.rlContent);
        }

    }

}
