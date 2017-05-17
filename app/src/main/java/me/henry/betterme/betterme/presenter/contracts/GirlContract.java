package me.henry.betterme.betterme.presenter.contracts;

import java.util.List;

import me.henry.betterme.betterme.model.MeiZhiJsonData;

/**
 * Created by zj on 2017/3/30.
 * me.henry.betterme.betterme.presenter.contracts
 */

public interface GirlContract {
    interface View  {
void refreshError();
void refreshFinished(List<MeiZhiJsonData.MeiZhi> datas);

    }
    interface Presenter  {
void startRefreshData(int page);


    }
}
