package me.henry.betterme.betterme.demo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.utils.Utils;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "SensorActivity";
    SensorManager mSensorManager;
    Sensor mAccelerometerSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        if (mSensorManager != null) {
            //获取加速度传感器
            mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (mAccelerometerSensor != null) {
                mSensorManager.registerListener(this, mAccelerometerSensor, SensorManager.SENSOR_DELAY_UI);
            }
        }
    }

    @Override
    protected void onPause() {
        // 务必要在pause中注销 mSensorManager
        // 否则会造成界面退出后摇一摇依旧生效的bug
        if (mSensorManager != null) {
            mSensorManager.unregisterListener(this);
        }
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int type = event.sensor.getType();
        //摇一摇功能
        if (type == Sensor.TYPE_ACCELEROMETER) {
            //获取三个方向值
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];
                if ((Math.abs(x) > 20 || Math.abs(y) > 20 || Math
                        .abs(z) > 20)) {
                    if (!Utils.isFastClick(1000)) {
                        Toast.makeText(SensorActivity.this, "shake", Toast.LENGTH_SHORT).show();
                    }
                }


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.e(TAG, "sensor=" + sensor.getName() + ".........................accuracy=" + accuracy);

    }
}
