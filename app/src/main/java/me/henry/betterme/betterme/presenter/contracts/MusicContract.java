package me.henry.betterme.betterme.presenter.contracts;

import java.util.ArrayList;

import me.henry.betterme.betterme.model.MusicInfo;

/**
 * Created by zj on 2017/3/30.
 * me.henry.betterme.betterme.presenter.contracts
 */

public interface MusicContract {
    interface View  {
void finishedLoadData(ArrayList<MusicInfo> musics);
    }
    interface Presenter  {
void loadDatas();

    }
}
