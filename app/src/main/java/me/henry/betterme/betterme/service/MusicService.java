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
import java.util.Collections;
import java.util.List;

import me.henry.betterme.betterme.IMusicInterface;
import me.henry.betterme.betterme.common.MyConstants;
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
    public List<MusicInfo> mNormalList=new ArrayList<>();//用来保存原有的顺序
    public BroadcastReceiver mUpdateMusicBroadCast;
    public int mMode = MyConstants.PlayMode_Loop;


    @Override
    public void onCreate() {
        super.onCreate();
        mUpdateMusicBroadCast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(MyConstants.Action_updateMusicList)) {
                    mMusicList = intent.getParcelableArrayListExtra("musiclist");
                    mNormalList.clear();
                    mNormalList.addAll(mMusicList);
                }
            }
        };
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                try {
                    if (mMode== MyConstants.PlayMode_OnlyOne){
                        mBinder.playMusic(currentMusicInfo,currentIndex);
                    }else {
                        mBinder.next();
                    }
                    Log.e(TAG, "onCompletion");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyConstants.Action_updateMusicList);
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
            Log.e(TAG, "playOrPause(),currentIndex=" + currentIndex);
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
                        currentIndex = 0;
                    }//如果next到最后一首就重新来

                  if (currentIndex>mMusicList.size()-1){currentIndex=mMusicList.size()-1;}//预防删除很多歌后currentindex溢出
                    mPlayer.setDataSource(mMusicList.get(currentIndex).data);
                    mPlayer.prepare();
                    mPlayer.seekTo(0);
                    mPlayer.start();
                    currentMusicInfo = mMusicList.get(currentIndex);
                    notifyChanges(MyConstants.Action_updateMusicInfo);
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
                    } else {
                        currentIndex = mMusicList.size() - 1;
                    }
                    Log.e(TAG, "previous(),currentIndex=" + currentIndex);
                    mPlayer.setDataSource(mMusicList.get(currentIndex).data);
                    mPlayer.prepare();
                    mPlayer.seekTo(0);
                    mPlayer.start();
                    currentMusicInfo = mMusicList.get(currentIndex);
                    notifyChanges(MyConstants.Action_updateMusicInfo);
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
                    Log.e(TAG, "playMusic(),currentIndex=" + currentIndex);
                    mPlayer.prepare();
                    mPlayer.seekTo(0);
                    mPlayer.start();
                    currentMusicInfo = mMusicList.get(currentIndex);
                    notifyChanges(MyConstants.Action_updateMusicInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void setPlayMode(int mode) throws RemoteException {
            mMode = mode;

            if (mode == MyConstants.PlayMode_Shuffle) {
                Collections.shuffle(mMusicList);
                for (int i = 0; i < mMusicList.size(); i++) {//将洗牌前的currentindex变成当前的currentindex
                    if (mMusicList.get(i).songId == currentMusicInfo.songId) {
                        currentIndex = i;
                    }
                }
            } else {
                if (mNormalList != null&&mNormalList.size()>0) {
                    mMusicList.clear();
                    mMusicList.addAll(mNormalList);
                    for (int i = 0; i < mMusicList.size(); i++) {
                        if (mMusicList.get(i).songId == currentMusicInfo.songId) {
                            currentIndex = i;
                        }
                    }
                }
            }
        }

        @Override
        public MusicInfo getCurrentMusicInfo() throws RemoteException {
            return currentMusicInfo;
        }
    };

    private void notifyChanges(String action) {
        if (action.equals(MyConstants.Action_updateMusicInfo)) {
            Utils.sendUpdateInfoBro(MusicService.this, currentMusicInfo);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mUpdateMusicBroadCast);
    }
}
