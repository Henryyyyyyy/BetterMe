package me.henry.betterme.betterme.http.callback;

import java.net.HttpURLConnection;

import me.henry.betterme.betterme.http.AppException;

/**
 * Created by zj on 2017/5/23.
 * me.henry.betterme.betterme.http
 */

public interface ICallBack<T> {
    void onSuccess(T result);
    void onFailed(AppException e);
    T parse(HttpURLConnection connection,OnProgressUpdatedListener onProgressUpdatedListener) throws AppException;
    T parse(HttpURLConnection connection) throws AppException;
    void onProgressUpdated(int curLen,int totalLen);
    void cancel();
}
