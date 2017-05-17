package me.henry.betterme.betterme.presenter;

import android.content.Context;

import me.henry.betterme.betterme.common.BasePresenter;
import me.henry.betterme.betterme.presenter.contracts.NoteContract;

/**
 * Created by zj on 2017/4/18.
 * me.henry.betterme.betterme.presenter
 */

public class NotePresenter extends BasePresenter<NoteContract.View>implements NoteContract.Presenter{
    public NotePresenter(Context context) {
        super(context);
    }

    @Override
    public void addNote() {

    }

    @Override
    public void deleteNote() {

    }

    @Override
    public void loadNote() {

    }
}
