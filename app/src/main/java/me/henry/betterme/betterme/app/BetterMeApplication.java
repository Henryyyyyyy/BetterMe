package me.henry.betterme.betterme.app;

import android.app.Activity;
import android.app.Application;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.squareup.leakcanary.LeakCanary;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import me.henry.betterme.betterme.api.WeatherApi;
import me.henry.betterme.betterme.common.MyConstants;
import me.henry.betterme.betterme.model.Person;
import me.henry.betterme.betterme.utils.SharedPresUtil;
import okhttp3.OkHttpClient;

/**
 * Created by 3 on 2017/2/10.
 */

public class BetterMeApplication extends Application {
    private static BetterMeApplication instance;
    private Set<Activity> allActivities;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    public static synchronized BetterMeApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        //初始化sharedpreference
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build(); //其他配置
       OkHttpUtils.initClient(okHttpClient);
        LeakCanary.install(this);
        //百度地图相关
        mLocationClient = new LocationClient(getApplicationContext());
        initLocation();
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.start();
        instance = this;


    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 0;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }


    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<Activity>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        Person.Builder builder = new Person.Builder();
        Person p = builder.name("aa").age(1).build();
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public class MyLocationListener implements BDLocationListener {
        boolean isGet = false;

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (!isGet){
                SharedPresUtil.setString(getApplicationContext(), MyConstants.SP_localcity, location.getCity());
                WeatherApi.getLocalWeather(getApplicationContext(),location.getCity());
                isGet=true;
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }


    }
}
