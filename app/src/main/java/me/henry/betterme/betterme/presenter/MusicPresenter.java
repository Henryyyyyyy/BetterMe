package me.henry.betterme.betterme.presenter;

import android.content.Context;

import java.util.ArrayList;

import me.henry.betterme.betterme.activity.MainActivity;
import me.henry.betterme.betterme.common.BasePresenter;
import me.henry.betterme.betterme.model.MusicInfo;
import me.henry.betterme.betterme.presenter.contracts.MusicContract;
import me.henry.betterme.betterme.utils.MusicUtil;

/**
 * Created by zj on 2017/3/30.
 * me.henry.betterme.betterme.presenter
 */

public class MusicPresenter extends BasePresenter<MusicContract.View>implements MusicContract.Presenter{
    public MusicPresenter(Context context) {
        super(context);
    }

    @Override
    public void loadDatas() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<MusicInfo> queryMusics = MusicUtil.queryMusic(mContext);
                ((MainActivity)mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (queryMusics != null) {
                            if (mView!=null){
                                mView.finishedLoadData(queryMusics);
                            }
                        }
                    }
                });
            }
        }).start();
    }
}
