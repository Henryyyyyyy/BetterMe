package me.henry.betterme.betterme.demo.videomediacontroller;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import me.henry.betterme.betterme.R;

public class VideoViewActivity extends Activity {
	private final String TAG = "main";
	private EditText et_path;
	private Button btn_play, btn_pause, btn_replay, btn_stop;
	private SeekBar seekBar;
	private VideoView vv_video;
	private boolean isPlaying;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_videoview);

		seekBar = (SeekBar) findViewById(R.id.seekBar);
		et_path = (EditText) findViewById(R.id.et_path);
		vv_video = (VideoView) findViewById(R.id.vv_videoview);

		btn_play = (Button) findViewById(R.id.btn_play);
		btn_pause = (Button) findViewById(R.id.btn_pause);
		btn_replay = (Button) findViewById(R.id.btn_replay);
		btn_stop = (Button) findViewById(R.id.btn_stop);

		btn_play.setOnClickListener(click);
		btn_pause.setOnClickListener(click);
		btn_replay.setOnClickListener(click);
		btn_stop.setOnClickListener(click);

		// 为进度条添加进度更改事件
		seekBar.setOnSeekBarChangeListener(change);
	}

	private OnSeekBarChangeListener change = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// 当进度条停止修改的时候触发
			// 取得当前进度条的刻度
			int progress = seekBar.getProgress();
			if (vv_video != null && vv_video.isPlaying()) {
				// 设置当前播放的位置
				vv_video.seekTo(progress);
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {

		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
									  boolean fromUser) {

		}
	};
	private View.OnClickListener click = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
				case R.id.btn_play:
					play(0);
					break;
				case R.id.btn_pause:
					pause();
					break;
				case R.id.btn_replay:
					replay();
					break;
				case R.id.btn_stop:
					stop();
					break;
				default:
					break;
			}
		}
	};

	protected void play(int msec) {
		Log.i(TAG, " 获取视频文件地址");
		String path = et_path.getText().toString().trim();
		File file = new File(path);
		if (!file.exists()) {
			Toast.makeText(this, "视频文件路径错误", Toast.LENGTH_SHORT).show();
			return;
		}

		Log.i(TAG, "指定视频源路径");
		vv_video.setVideoPath(file.getAbsolutePath());
		Log.i(TAG, "开始播放");
		vv_video.start();

		// 按照初始位置播放
		vv_video.seekTo(msec);
		// 设置进度条的最大进度为视频流的最大播放时长
		seekBar.setMax(vv_video.getDuration());

		// 开始线程，更新进度条的刻度
		new Thread() {

			@Override
			public void run() {
				try {
					isPlaying = true;
					while (isPlaying) {
						// 如果正在播放，没0.5.毫秒更新一次进度条
						int current = vv_video.getCurrentPosition();
						seekBar.setProgress(current);

						sleep(500);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
		// 播放之后设置播放按钮不可用
		btn_play.setEnabled(false);

		vv_video.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// 在播放完毕被回调
				btn_play.setEnabled(true);
			}
		});

		vv_video.setOnErrorListener(new OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				// 发生错误重新播放
				play(0);
				isPlaying = false;
				return false;
			}
		});
	}

	/**
	 * 重新开始播放
	 */
	protected void replay() {
		if (vv_video != null && vv_video.isPlaying()) {
			vv_video.seekTo(0);
			Toast.makeText(this, "重新播放", Toast.LENGTH_SHORT).show();
			btn_pause.setText("暂停");
			return;
		}
		isPlaying = false;
		play(0);

	}

	/**
	 * 暂停或继续
	 */
	protected void pause() {
		if (btn_pause.getText().toString().trim().equals("继续")) {
			btn_pause.setText("暂停");
			vv_video.start();
			Toast.makeText(this, "继续播放", Toast.LENGTH_SHORT).show();
			return;
		}
		if (vv_video != null && vv_video.isPlaying()) {
			vv_video.pause();
			btn_pause.setText("继续");
			Toast.makeText(this, "暂停播放", Toast.LENGTH_SHORT).show();
		}
	}

	/*
	 * 停止播放
	 */
	protected void stop() {
		if (vv_video != null && vv_video.isPlaying()) {
			vv_video.stopPlayback();
			btn_play.setEnabled(true);
			isPlaying = false;
		}
	}
}
