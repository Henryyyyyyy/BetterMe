package me.henry.betterme.betterme.http.callback;


import android.util.Log;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import me.henry.betterme.betterme.http.AppException;
import me.henry.betterme.betterme.http.callback.AbstractCallBack;

/**
 * Created by zj on 2017/5/26.
 * me.henry.betterme.betterme.http
 * 如果抽象类已经实现了接口中的某个方法，那么继承这个抽象类后，就不用再实现已经在抽象类实现的方法了
 */

public abstract class JsonCallBack<T> extends AbstractCallBack<T> {
    @Override
    protected T bindData(String result) throws AppException {

        try {
            JSONObject json=new JSONObject(result);
            JSONObject data=json.getJSONObject("data");
            Gson gson=new Gson();
            Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            return gson.fromJson(data.toString(), type);
        } catch (JSONException e) {
            throw  new AppException(AppException.ErrorType.JSON,e.getMessage());
        }

    }
}
