package me.henry.betterme.betterme.demo.videomediacontroller;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import me.henry.betterme.betterme.R;


public class Main3Activity extends Activity implements OnClickListener {
	private Button btn_videoview, btn_controller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);

		btn_videoview = (Button) findViewById(R.id.btn_videoview);
		btn_controller = (Button) findViewById(R.id.btn_controller);

		btn_videoview.setOnClickListener(this);
		btn_controller.setOnClickListener(this);
		Dexter.withActivity(this)
						.withPermissions(
						Manifest.permission.READ_EXTERNAL_STORAGE,
						Manifest.permission.WRITE_EXTERNAL_STORAGE)
						.withListener(new MultiplePermissionsListener() {
					@Override
					public void onPermissionsChecked(MultiplePermissionsReport report) {
					}

							@Override
							public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> permissions, PermissionToken token) {

							}


						}).check();
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
			case R.id.btn_videoview:
				intent=new Intent(Main3Activity.this, VideoViewActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_controller:
				intent=new Intent(Main3Activity.this, ControllerActivity.class);
				startActivity(intent);
				break;
			default:
				break;
		}

	}
public void verifyPermission(){

}
}