package me.henry.betterme.betterme.http;

import android.app.Application;
import android.os.AsyncTask;
import java.net.HttpURLConnection;
import me.henry.betterme.betterme.http.callback.OnProgressUpdatedListener;

/**
 * Created by zj on 2017/5/23.
 * me.henry.betterme.betterme.http
 */

/**
 * 三个参数分别代表：
 * 1.请求需要的参数
 * 2.progress返回值
 * 3.请求之后的返回值
 */
public class RequestTask extends AsyncTask<Void, Integer, Object> {
    public Request request;
    public RequestTask(Request request) {
        this.request = request;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Object doInBackground(Void... params) {
   return request(0);
    }

    public Object request(int retry) {
        try{
            HttpURLConnection connection = HttpEngine.execute(request);
            if (request.isProgressUpdate) {
                return request.iCallBack.parse(connection, new OnProgressUpdatedListener() {
                    @Override
                    public void onProgressUpdated(int curLen, int totalLen) {
                        publishProgress(curLen, totalLen);
                    }
                });
            } else {
                return request.iCallBack.parse(connection);
            }
        } catch (AppException e) {
            if (e.type==AppException.ErrorType.TIMEOUT){
                if (retry < request.maxRetryCount) {
                    retry++;
                    return request(retry);
                }
            }
            return e;
        }
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof AppException) {
            if (request.onGlobalExceptionListener!=null){
                if (!request.onGlobalExceptionListener.handleException((AppException) o)){
                    //可能还有其他错误，如果handle了就返回true，不handle，就应该返回false，全局返回false
                    request.iCallBack.onFailed((AppException) o);

                }
            }
        } else {
            request.iCallBack.onSuccess(o);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        request.iCallBack.onProgressUpdated(values[0], values[1]);
    }
}
