package me.henry.betterme.betterme.demo.adversdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import me.henry.betterme.betterme.R;
import me.henry.scrollads.ScrollAdsConfig;
import okhttp3.Call;

public class UploadClientActivity extends AppCompatActivity {
public Button upLoadBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_client);
        upLoadBtn= (Button) findViewById(R.id.upLoadBtn);
        upLoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file=new File(ScrollAdsConfig.FILE_DIR+File.separator+"asfs.jpg");
                File file2=new File(ScrollAdsConfig.FILE_DIR+File.separator+"zj.png");
                File file3=new File(ScrollAdsConfig.FILE_DIR+File.separator+"kwl.png");
               // String url="http://192.168.11.18:8060";
                String url="http://192.168.43.196:8060";
                OkHttpUtils.post()//
                        .addFile("asfs.jpg", "asfs.jpg", file)
                        .addFile("zj.png", "zj.png", file2)
                        .addFile("kwl.png", "kwl.png", file3)
                        .url(url)
                        .build()//
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("iloveyyy","failed response="+e.toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("iloveyyy","success response="+response);
                            }
                        });
            }
        });
    }
}
