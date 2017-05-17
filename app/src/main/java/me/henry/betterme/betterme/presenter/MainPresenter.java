package me.henry.betterme.betterme.presenter;

import android.content.Context;
import me.henry.betterme.betterme.common.BasePresenter;
import me.henry.betterme.betterme.presenter.contracts.MainContract;

/**
 * Created by 3 on 2017/2/23.
 */

public class MainPresenter extends BasePresenter<MainContract.View>implements MainContract.Presenter{

    public MainPresenter(Context context) {
        super(context);
    }

    @Override
    public void switchPager(int index) {
        mView.showTips();
    }
}
