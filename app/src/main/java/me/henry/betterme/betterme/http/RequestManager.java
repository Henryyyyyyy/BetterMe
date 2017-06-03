package me.henry.betterme.betterme.http;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zj on 2017/6/3.
 * me.henry.betterme.betterme.http
 */

public class RequestManager {
   private static RequestManager mInstance;
    private ArrayList<Request> requests;
    private final ExecutorService mExecutors;
    private HashMap<String, ArrayList<Request>> mCachedRequest;
    public static RequestManager getInstance() {
        if (mInstance == null) {
            mInstance = new RequestManager();
        }
        return mInstance;
    }

    public RequestManager() {
        mCachedRequest = new HashMap<>();
        mExecutors = Executors.newFixedThreadPool(5);
    }

    public void execute(Request request){
        request.execute(mExecutors);
        //这里的tag指的是带有同一tag的一系列request
        if (request.tag == null) {
            return;// no need to cache the request
        }
        if (!mCachedRequest.containsKey(request.tag)) {
            ArrayList<Request> requests = new ArrayList<Request>();
            mCachedRequest.put(request.tag, requests);
        }
        mCachedRequest.get(request.tag).add(request);

    }
}
