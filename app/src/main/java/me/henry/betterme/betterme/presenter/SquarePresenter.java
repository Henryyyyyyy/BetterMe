package me.henry.betterme.betterme.presenter;

import android.content.Context;

import me.henry.betterme.betterme.common.BasePresenter;
import me.henry.betterme.betterme.presenter.contracts.SquareContract;

/**
 * Created by zj on 2017/7/20.
 * me.henry.betterme.betterme.presenter
 */

public class SquarePresenter extends BasePresenter<SquareContract.View>{
    public SquarePresenter(Context context) {
        super(context);
    }
}
