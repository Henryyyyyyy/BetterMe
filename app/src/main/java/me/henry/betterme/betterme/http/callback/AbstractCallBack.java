package me.henry.betterme.betterme.http.callback;


import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import me.henry.betterme.betterme.http.AppException;

/**
 * Created by zj on 2017/5/26.
 * me.henry.betterme.betterme.http
 * 1.如果抽象类已经实现了接口中的某个方法，那么继承这个抽象类后，就不用再实现已经在抽象类实现的方法了
 * 2.该类只负责android层面上的http连接，对于数据的处理交给子类
 * 3.下面这段代码为什么可以new抽象类？
 * 答：抽象类不能直接new出来，如果要new出来必须使用匿名类的方式，匿名类其实就是声明了一个新的类来继承抽象类，所以你必须在匿名类实现所有抽象方法。
 *  request.setCallBack(new JsonCallBack<StayUser>() {
        @Override
        public void onSuccess(StayUser result) {
            Log.e("keith","o??????="+result.toString());
                }
 */

public abstract class AbstractCallBack<T> implements ICallBack<T> {
private String path;
    private volatile boolean isCancelled;
    @Override
    public T parse(HttpURLConnection connection,OnProgressUpdatedListener listener) throws AppException {
        try {
            checkIfCancelled();
            int status = connection.getResponseCode();
            if (status == 200) {
                if (path==null){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    InputStream is = connection.getInputStream();
                    int totalLen=connection.getContentLength();
                    int curLen=0;
                    byte[] buffer = new byte[2048];
                    int len;
                    while ((len = is.read(buffer)) != -1) {
                        checkIfCancelled();
                        out.write(buffer, 0, len);
                        curLen+=len;
                        listener.onProgressUpdated(curLen,totalLen);
                    }
                    out.flush();
                    out.close();
                    String result = new String(out.toByteArray());
                    Log.e("henrysss","result========="+result);
                    return bindData(result);
                }else {
                    FileOutputStream out = new FileOutputStream(path);
                    InputStream is = connection.getInputStream();
                    int totalLen=connection.getContentLength();
                    int curLen=0;
                    byte[] buffer = new byte[2048];
                    int len;
                    while ((len = is.read(buffer)) != -1) {
                        checkIfCancelled();
                        out.write(buffer, 0, len);
                        curLen+=len;
                        listener.onProgressUpdated(curLen,totalLen);

                    }
                    out.flush();
                    out.close();
                    return bindData(path);
                }

            }
        } catch (IOException e) {
            throw new AppException(AppException.ErrorType.SERVER, e.getMessage());
        }
        return null;
    }

    @Override
    public T parse(HttpURLConnection connection) throws AppException {
        return parse(connection,null);
    }

    @Override
    public void onProgressUpdated(int curLen, int totalLen) {

    }
    protected void checkIfCancelled() throws AppException {
        if (isCancelled) {
            throw new AppException(AppException.ErrorType.CANCEL, "the request has been cancelled");
        }
    }
    protected abstract T bindData(String result) throws AppException;

    @Override
    public void cancel() {
        isCancelled=true;
    }

    public ICallBack setCachePath(String path) {
        this.path = path;
        return this;
    }
}
