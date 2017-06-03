package me.henry.betterme.betterme.http;

import android.os.Build;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import me.henry.betterme.betterme.http.callback.ICallBack;

/**
 * Created by zj on 2017/5/23.
 * me.henry.betterme.betterme.http
 */

public class Request {
    public String url;
    public ICallBack iCallBack;
    public RequestTask task;
    public RequestMethod method;
    public RequestTool tool;
    public String content;
    public Map<String, String> headers;
    public int maxRetryCount = 3;
    public boolean isProgressUpdate;
    public OnGlobalExceptionListener onGlobalExceptionListener;
    public volatile boolean isCancelled;
    public String tag;

    public void setGlobalExceptionListener(OnGlobalExceptionListener onGlobalExceptionListener) {
        this.onGlobalExceptionListener = onGlobalExceptionListener;
    }
    public void enableProgressUpdated(boolean b) {
        isProgressUpdate = b;
    }

    public enum RequestMethod {GET, POST, PUT, DELETE}

    public enum RequestTool {OKHTTP, URLCONNECTION}

    public Request(String url, RequestMethod method) {
        this.url = url;
        this.method = method;
        this.tool = RequestTool.URLCONNECTION;
    }

    public Request(String url, RequestMethod method, RequestTool tool) {
        this.url = url;
        this.method = method;
        this.tool = tool;
    }

    public Request(String url) {
        this.url = url;
        this.method = RequestMethod.GET;
        this.tool = RequestTool.URLCONNECTION;
    }

    public Request(String url, RequestTool tool) {
        this.url = url;
        this.method = RequestMethod.GET;
        this.tool = tool;
    }

    public void addHeader(String key, String value) {
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put(key, value);
    }
    public void checkIfCancelled() throws AppException {
        if (isCancelled){
            throw new AppException(AppException.ErrorType.CANCEL,"the request has been cancelled");
        }
    }
    public void cancel(boolean force) {
        isCancelled = true;
        iCallBack.cancel();

    }
    public void setCallBack(ICallBack callBack) {
        this.iCallBack = callBack;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public void execute(Executor mExecutors) {
        task = new RequestTask(this);
        if (Build.VERSION.SDK_INT > 11) {
            task.executeOnExecutor(mExecutors);
        } else {
            task.execute();
        }
    }
}
