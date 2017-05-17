package me.henry.betterme.betterme.app;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import me.henry.betterme.betterme.model.Person;
import okhttp3.OkHttpClient;

/**
 * Created by 3 on 2017/2/10.
 */

public class BetterMeApplication extends Application {
    private static BetterMeApplication instance;
    private Set<Activity> allActivities;


    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        LeakCanary.install(this);
        instance = this;
    }

    public static synchronized BetterMeApplication getInstance() {
        return instance;
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
        Person.Builder builder=new Person.Builder();
        Person p=builder.name("aa").age(1).build();
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

}
