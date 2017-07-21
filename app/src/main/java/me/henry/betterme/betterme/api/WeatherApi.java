package me.henry.betterme.betterme.api;

import android.content.Context;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import me.henry.betterme.betterme.common.MyConstants;
import me.henry.betterme.betterme.utils.SharedPresUtil;
import okhttp3.Call;

/**
 * Created by zj on 2017/6/8.
 * me.henry.betterme.betterme.api
 */

public class WeatherApi {

    public static void getLocalWeather(final Context context, String city) {
        SharedPresUtil.setString(context, MyConstants.SP_weather, "雨");
//        String url = "http://jisutianqi.market.alicloudapi.com/weather/query";
//        String appcode = "84bd02113baa4a8cbca39b17fea034d5";
//        if (city != null) {
//            OkHttpUtils.get()
//                    .addHeader("Authorization", "APPCODE " + appcode)
//                    .addParams("city", city)
//                    .url(url)
//                    .build()
//                    .execute(new StringCallback() {
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//
//                        }
//
//                        @Override
//                        public void onResponse(String response, int id) {
//                            try {
//                                JSONObject jo = new JSONObject(response);
//                                String msg = jo.getString("msg");
//                                if (msg.equals("ok")) {
//                                    JSONObject result = jo.getJSONObject("result");
//                                    String weather = result.getString("weather");
//                                    Log.e("weee","weather="+weather);
//                                    if (weather.contains("雨")) {
//                                        SharedPresUtil.setString(context, MyConstants.SP_weather, "雨");
//                                    } else if (weather.contains("雪")) {
//                                        SharedPresUtil.setString(context, MyConstants.SP_weather, "雪");
//                                    } else {
//                                        SharedPresUtil.setString(context, MyConstants.SP_weather, "晴");
//                                    }
//
//
//                                }
//
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                    });
//
//        }

    }
}
