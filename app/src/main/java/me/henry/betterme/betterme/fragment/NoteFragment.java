package me.henry.betterme.betterme.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.adapter.MusicAdapter;
import me.henry.betterme.betterme.adapter.NoteAdapter;
import me.henry.betterme.betterme.common.BaseFragment;
import me.henry.betterme.betterme.db.DBManager;
import me.henry.betterme.betterme.db.DBUtil;
import me.henry.betterme.betterme.model.Note;
import me.henry.betterme.betterme.presenter.NotePresenter;
import me.henry.betterme.betterme.presenter.contracts.NoteContract;

import static me.henry.betterme.betterme.R.id.rvMusicList;


public class NoteFragment extends BaseFragment<NoteContract.View, NotePresenter> {
    @BindView(R.id.tvAddNote)
    TextView tvAddNote;
    @BindView(R.id.tvDeleteNote)
    TextView tvDeleteNote;
    @BindView(R.id.rvNote)
    RecyclerView rvNote;
    NoteAdapter mAdapter;
    public NoteFragment(Context Context) {
        super(Context);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_note;
    }

    @Override
    protected NotePresenter initPresenter() {
        return new NotePresenter(getActivity());
    }

    @Override
    protected void initViewEventDataInCreate() {
        List<Note> datas= DBManager.getInstance(getContext()).queryAll(Note.class);
              mAdapter = new NoteAdapter(mContext, datas, R.layout.item_note);
        rvNote.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNote.setAdapter(mAdapter);



    }

    @Override
    protected void firstLoadData() {

    }


}
