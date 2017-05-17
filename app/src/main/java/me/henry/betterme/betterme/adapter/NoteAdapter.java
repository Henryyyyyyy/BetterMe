package me.henry.betterme.betterme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.model.Note;
import me.henry.betterme.betterme.view.Recycler.version1.BaseRvAdapter;

/**
 * Created by zj on 2017/4/19.
 * me.henry.betterme.betterme.adapter
 */

public class NoteAdapter extends BaseRvAdapter<Note>{

    public NoteAdapter(Context mContext, List<Note> mDatas, int itemLayoutId) {
        super(mContext, mDatas, itemLayoutId);
    }

    @Override
    protected RecyclerView.ViewHolder setViewHolder(View itemView, int viewType) {
        return new NoteHolder(itemView);
    }

    @Override
    protected void onBindData(RecyclerView.ViewHolder holder, int position) {

    }

    class NoteHolder extends RecyclerView.ViewHolder {
        public NoteHolder(View itemView) {
            super(itemView);

        }

    }
}
