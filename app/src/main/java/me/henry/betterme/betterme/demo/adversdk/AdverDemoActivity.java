package me.henry.betterme.betterme.demo.adversdk;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.henry.betterme.betterme.R;
import me.henry.scrollads.Advert;
import me.henry.scrollads.ScrollAds;
import me.henry.scrollads.ScrollAdsConfig;
import me.henry.scrollads.httpserver.myservers.ServerConfig;
import me.henry.scrollads.httpserver.myservers.SevenServer;
import me.henry.scrollads.utils.CloudPresUtil;


public class AdverDemoActivity extends AppCompatActivity {
    ScrollAds demoScrollAds;
    List<Advert> ads = new ArrayList<>();
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
         private static String[] PERMISSIONS_STORAGE = {
                       Manifest.permission.READ_EXTERNAL_STORAGE,
                     Manifest.permission.WRITE_EXTERNAL_STORAGE };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adver_demo);
        if (CloudPresUtil.getString(this,ServerConfig.Pref_Account)==null){
            CloudPresUtil.setString(this, ServerConfig.Pref_Account,ServerConfig.Default_Account);
        }
        if (CloudPresUtil.getString(this,ServerConfig.Pref_Password)==null){
            CloudPresUtil.setString(this, ServerConfig.Pref_Password,ServerConfig.Default_Password);

        }
        verifyStoragePermissions(this);
        demoScrollAds = (ScrollAds) findViewById(R.id.demoScrollAds);
        ads= ScrollAdsConfig.getFileToAds(new int[]{R.drawable.banner1,R.drawable.banner2,R.drawable.banner3},null);
        demoScrollAds.setDelayTime(5000)
                     .setAdverts(ads)
                     .start();
        try {
            SevenServer server=new SevenServer(8060,this);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String token= new MyResponse(200, "修改密码成功,登录成功", "").createResponse();
//        Log.e("wwe","token="+token);
//    new Handler().postDelayed(new Runnable() {
//    @Override
//    public void run() {
//        List<Advert>newads=new ArrayList<Advert>();
//        for (int i = 0; i < 5; i++) {
//            Advert a=new Advert();
//            a.type=0;
//            a.isEmptyType=true;
//            a.res=R.drawable.banner11;
//                newads.add(a);
//        }
//
//  demoScrollAds.loadNewData(newads,AdverDemoActivity.this);
//    }
//}, 15000);
    }



    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }
}
