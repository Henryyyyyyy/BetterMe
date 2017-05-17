package me.henry.betterme.betterme.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.henry.betterme.betterme.IMusicInterface;
import me.henry.betterme.betterme.common.Constants;
import me.henry.betterme.betterme.model.MusicInfo;
import me.henry.betterme.betterme.utils.Utils;


/**
 * Created by zj on 2017/4/11.
 * me.henry.betterme.betterme.service
 */

public class MusicService extends Service {
    public static final String TAG = "MusicService";
    public static MediaPlayer mPlayer = new MediaPlayer();
    public static int currentIndex = 0;
    public static MusicInfo currentMusicInfo = null;
    public List<MusicInfo> mMusicList = new ArrayList<>();
    public BroadcastReceiver mUpdateMusicBroadCast;

    @Override
    public void onCreate() {
        super.onCreate();
        mUpdateMusicBroadCast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Constants.Action_updateMusicList)) {
                    mMusicList = intent.getParcelableArrayListExtra("musiclist");
                }
            }
        };
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                try {
                    mBinder.next();
                    Log.e(TAG,"onCompletion");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.Action_updateMusicList);
        registerReceiver(mUpdateMusicBroadCast, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IMusicInterface.Stub mBinder = new IMusicInterface.Stub() {
        @Override
        public void playOrPause() throws RemoteException {
            Log.e(TAG,"playOrPause(),currentIndex="+currentIndex);
            if (mPlayer.isPlaying()) {
                mPlayer.pause();
            } else {
                mPlayer.start();
            }
        }

        @Override
        public void next() throws RemoteException {
            if (mPlayer != null) {

                try {
                    mPlayer.reset();
                    currentIndex++;
                    if (currentIndex > mMusicList.size() - 1) {
                        currentIndex = 0;//如果next到最后一首就重新来
                    }
                    Log.e(TAG,"next(),currentIndex="+currentIndex);
                    mPlayer.setDataSource(mMusicList.get(currentIndex).data);
                    mPlayer.prepare();
                    mPlayer.seekTo(0);
                    mPlayer.start();
                    currentMusicInfo = mMusicList.get(currentIndex);
                    notifyChanges(Constants.Action_updateMusicInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void previous() throws RemoteException {
            if (mPlayer != null) {

                try {
                    mPlayer.reset();
                    if (currentIndex != 0) {
                        currentIndex--;//如果已经减到第一首了，就不给它继续减下去
                    }
                    Log.e(TAG,"previous(),currentIndex="+currentIndex);
                    mPlayer.setDataSource(mMusicList.get(currentIndex).data);
                    mPlayer.prepare();
                    mPlayer.seekTo(0);
                    mPlayer.start();
                    currentMusicInfo = mMusicList.get(currentIndex);
                    notifyChanges(Constants.Action_updateMusicInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void getCurrentPosition() throws RemoteException {
        }

        @Override
        public void playMusic(MusicInfo music, int index) throws RemoteException {
            if (mPlayer != null) {

                try {

                    mPlayer.reset();
                    mPlayer.setDataSource(music.data);
                    currentIndex = index;
                    Log.e(TAG,"playMusic(),currentIndex="+currentIndex);
                    mPlayer.prepare();
                    mPlayer.seekTo(0);
                    mPlayer.start();
                    currentMusicInfo = mMusicList.get(currentIndex);
                    notifyChanges(Constants.Action_updateMusicInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public MusicInfo getCurrentMusicInfo() throws RemoteException {
            return currentMusicInfo;
        }
    };

    private void notifyChanges(String action) {
        if (action.equals(Constants.Action_updateMusicInfo)) {
            Utils.sendUpdateInfoBro(MusicService.this, currentMusicInfo);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mUpdateMusicBroadCast);
    }
}
