package me.henry.betterme.betterme.demo.videomediacontroller;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import me.henry.betterme.betterme.R;
import me.henry.scrollads.MyVideoView;

public class ControllerActivity extends Activity {
	private MyVideoView vv_video;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controller);
		vv_video=(MyVideoView) findViewById(R.id.vv_video);
		Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/kki.mp4");
		Log.e("haha","存在该路径="+Environment.getExternalStorageDirectory().getPath());
		vv_video.setVideoURI(uri);
		vv_video.start();
		vv_video.requestFocus();
		// 实例化MediaController
		//mController=new MediaController(this);
		//File file=new File("/sdcard/kki.mp4");
		//File file=new File("/storage/sdcard1/kki.mp4");
		//File file = new File(Environment.getExternalStorageDirectory(),"kki.mp4");
//		if(file.exists()){
//			Log.e("haha","存在该路径");
//			// 设置播放视频源的路径
//			vv_video.setVideoPath(file.getAbsolutePath());
//			vv_video.seekTo(0);
//			vv_video.start();
			// 为VideoView指定MediaController
			//vv_video.setMediaController(mController);
			// 为MediaController指定控制的VideoView
			//mController.setMediaPlayer(vv_video);
			// 增加监听上一个和下一个的切换事件，默认这两个按钮是不显示的
//			mController.setPrevNextListeners(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					Toast.makeText(ControllerActivity.this, "下一个",0).show();
//				}
//			}, new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					Toast.makeText(ControllerActivity.this, "上一个",0).show();
//				}
//			});
	//	}
	}

}
