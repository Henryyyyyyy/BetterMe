package me.henry.betterme.betterme.fragment;



import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import java.util.ArrayList;

import butterknife.BindView;
import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.adapter.MusicAdapter;
import me.henry.betterme.betterme.common.BaseFragment;
import me.henry.betterme.betterme.model.MusicInfo;
import me.henry.betterme.betterme.presenter.MusicPresenter;
import me.henry.betterme.betterme.presenter.contracts.MusicContract;
import me.henry.betterme.betterme.service.MusicPlayer;
import me.henry.betterme.betterme.utils.Utils;
import me.henry.betterme.betterme.view.Recycler.version1.BaseRvAdapter;

import static me.henry.betterme.betterme.service.MusicService.currentMusicInfo;



public class MusicFragment extends BaseFragment<MusicContract.View, MusicPresenter> implements MusicContract.View, View.OnClickListener {
    public static final String TAG = "MusicFragment";
    @BindView(R.id.rvMusicList)
    RecyclerView rvMusicList;
    private ArrayList<MusicInfo> mMusics = new ArrayList<>();
    private MusicAdapter mAdapter;

    public MusicFragment(Context Context) {
        super(Context);
    }

    @Override
    protected MusicPresenter initPresenter() {
        return new MusicPresenter(mContext);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_music;
    }

    @Override
    protected void initViewEventDataInCreate() {
        MusicPlayer.bindToService(getActivity());
        mAdapter = new MusicAdapter(mContext, mMusics, R.layout.item_music);
        rvMusicList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMusicList.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseRvAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e(TAG,"position="+position);
                MusicPlayer.playMusic(mMusics.get(position), position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });


    }



    @Override
    protected void firstLoadData() {
        mPresenter.loadDatas();
    }


    @Override
    public void finishedLoadData(ArrayList<MusicInfo> musics) {
        mAdapter.setDatas(musics);
        mMusics = musics;
        Utils.sendUpdateBrocastList(getActivity(), musics);
        currentMusicInfo = MusicPlayer.getCurrentMusicInfo();

    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onClick(View v) {

    }
}
