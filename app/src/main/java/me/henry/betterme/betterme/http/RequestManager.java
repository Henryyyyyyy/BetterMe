package me.henry.betterme.betterme.http;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by zj on 2017/6/3.
 * me.henry.betterme.betterme.http
 */

public class RequestManager {
   private static RequestManager mInstance;
private ArrayList<Request> requests;
    public static RequestManager getInstance() {
        if (mInstance == null) {
            mInstance = new RequestManager();
        }
        return mInstance;
    }
}
