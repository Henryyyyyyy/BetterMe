package me.henry.betterme.betterme.http;

import android.os.AsyncTask;

import java.io.Externalizable;
import java.io.IOException;

/**
 * Created by zj on 2017/5/23.
 * me.henry.betterme.betterme.http
 *
 */
/**
 * 三个参数分别代表：
 * 1.请求需要的参数
 * 2.progress返回值
 * 3.请求之后的返回值
 */
public class RequestTask extends AsyncTask<Void,Integer,Object>{
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
        try {
            return  HttpUrlConnectionUtil.execute(request);
        } catch (IOException e) {
           return e;
        }
    }
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (o instanceof Exception){
            request.iCallBack.onFailed((Exception)o);
        }else {
            request.iCallBack.onSuccess((String)o);
        }
    }
}
