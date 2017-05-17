package me.henry.betterme.betterme.presenter.contracts;

/**
 * Created by 3 on 2017/2/23.
 */

public interface MainContract {
    interface View  {
        void showTips();
    }
    interface Presenter  {
        void switchPager(int index);

    }
}
