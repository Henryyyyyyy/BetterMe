package me.henry.betterme.betterme.http;

/**
 * Created by zj on 2017/5/23.
 * me.henry.betterme.betterme.http
 */

public interface ICallBack {
    void onSuccess(String result);
    void onFailed(Exception e);

}
