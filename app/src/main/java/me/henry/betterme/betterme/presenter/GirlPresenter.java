package me.henry.betterme.betterme.presenter;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import me.henry.betterme.betterme.common.BasePresenter;
import me.henry.betterme.betterme.http.AppException;
import me.henry.betterme.betterme.http.Request;
import me.henry.betterme.betterme.http.RequestManager;
import me.henry.betterme.betterme.http.callback.StringCallBack;
import me.henry.betterme.betterme.model.MeiZhiJsonData;
import me.henry.betterme.betterme.presenter.contracts.GirlContract;

/**
 * Created by zj on 2017/3/30.
 * me.henry.betterme.betterme.presenter
 */

public class GirlPresenter extends BasePresenter<GirlContract.View> implements GirlContract.Presenter {
    public static final String TAG = "GirlPresenter";

    public GirlPresenter(Context context) {
        super(context);
    }

    @Override
    public void startRefreshData(int page) {
        Log.e(TAG, "startRefreshData=" + page);
        String url = "http://gank.io/api/data/福利/10/" + page;

        Request request = new Request(url, Request.RequestMethod.GET);
        request.setCallBack(new StringCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "result=" + result);
                Gson gson = new Gson();
                MeiZhiJsonData datas = gson.fromJson(result, MeiZhiJsonData.class);
                if (mView != null) {
                    mView.refreshFinished(datas.getResults());
                }
            }

            @Override
            public void onFailed(AppException e) {

            }
        });
        RequestManager.getInstance().execute(request);
//        OkHttpUtils.get().url(url).build().execute(new StringCallback(){
//            @Override
//            public void onError(Call call, Exception e, int id) {}
//            @Override
//            public void onResponse(String response, int id) {
//                Gson gson = new Gson();
//                MeiZhiJsonData datas = gson.fromJson(response, MeiZhiJsonData.class);
//                if (mView!=null){
//                    mView.refreshFinished(datas.getResults());
//                }
//            }
//        });
    }
}
