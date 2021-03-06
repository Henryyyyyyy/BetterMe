package me.henry.betterme.betterme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import java.io.File;
import java.util.concurrent.TimeUnit;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.app.BetterMeApplication;
import me.henry.betterme.betterme.demo.adversdk.AdverDemoActivity;
import me.henry.betterme.betterme.demo.colorchange.ColorChangeActivity;
import me.henry.betterme.betterme.http.AppException;
import me.henry.betterme.betterme.http.Request;
import me.henry.betterme.betterme.http.RequestManager;
import me.henry.betterme.betterme.http.callback.FileCallBack;
import me.henry.scrollads.utils.ABFileUtil;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

public class SplashActivity extends AppCompatActivity {
    public static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_splash);
        ABFileUtil.creatSDDir("/AdverFiles");//创建文件目录，用于放广告图片
        jumpPage();
        BetterMeApplication.getInstance().addActivity(this);

    }


    private void testDownLoadWithProgress() {

        String url = "http://api.stay4it.com/uploads/test.jpg";
        String path = Environment.getExternalStorageDirectory() + File.separator + "654ppp.jpg";
        final Request request = new Request(url, Request.RequestMethod.GET);

        request.setCallBack(new FileCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("keith", "---onSuccess=" + result.toString());
            }

            @Override
            public void onFailed(AppException e) {

            }

            @Override
            public void onProgressUpdated(int curLen, int totalLen) {
                super.onProgressUpdated(curLen, totalLen);
                Log.e("keith", "download=" + curLen + "/" + totalLen);

                if (curLen * 100l / totalLen > 50) {

                    request.cancel();
                }
            }
        }.setCachePath(path));
        RequestManager.getInstance().execute(request);
        request.enableProgressUpdated(true);

    }

    public void jumpPage() {

        final int count = 1;
        Observable.interval(0, 1, TimeUnit.SECONDS)//定义时间间隔(延迟执行，时间间隔,单位)
                .take(count + 1)//做多少次操作
                .map(new Func1<Long, Long>() {//定义返回数据
                    @Override
                    public Long call(Long aLong) {//第几次执行（次数的index），比如执行6次，就是0~5
                        Log.e(TAG, "along=" + aLong);
                        return count - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {//如果要添加倒计时按钮可以加个doonsubscribe，在开始的时候设置按钮enable，如果新建的是subscriber，则用onstart
                    @Override
                    public void onCompleted() {
//                            Intent intent=new Intent(SplashActivity.this,AdverDemoActivity.class);
                            Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BetterMeApplication.getInstance().removeActivity(this);
    }


}
