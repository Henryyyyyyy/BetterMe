package me.henry.betterme.betterme.presenter.contracts;

import java.util.ArrayList;

import me.henry.betterme.betterme.model.MusicInfo;

/**
 * Created by zj on 2017/4/18.
 * me.henry.betterme.betterme.presenter.contracts
 */

public interface NoteContract {
    interface View  {

    }
    interface Presenter  {
        void addNote();
        void deleteNote();
        void loadNote();

    }
}
