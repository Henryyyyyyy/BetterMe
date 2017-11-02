package me.henry.betterme.betterme.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import me.henry.betterme.betterme.common.MyConstants;
import me.henry.betterme.betterme.model.MusicInfo;

/**
 * Created by zj on 2017/4/14.
 * me.henry.betterme.betterme.utils
 */

public class Utils {
    public static void sendUpdateBrocastList(Context context, ArrayList<MusicInfo> musiclist) {
        Intent intent = new Intent();
        intent.setAction(MyConstants.Action_updateMusicList);
        intent.putParcelableArrayListExtra("musiclist", musiclist);
        Log.e("ggg", "sendBroadcast");
        context.sendBroadcast(intent);
    }

    public static void sendUpdateInfoBro(Context context, MusicInfo music) {
        Intent intent = new Intent();
        intent.setAction(MyConstants.Action_updateMusicInfo);
        intent.putExtra("music", music);
        context.sendBroadcast(intent);
    }
    public static void sendUpdatePlayState(Context context, boolean isPlaying) {
        Intent intent = new Intent();
        intent.setAction(MyConstants.Action_updatePlayState);
        intent.putExtra("isPlaying", isPlaying);
        context.sendBroadcast(intent);
    }
    /**
     * 是否快速点击
     * @param minTime
     * @return
     */
    private static long lastClickTime;
    private static int minTime = 1000;
    public static boolean isFastClick(int minTime) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD > 0 && timeD < minTime) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}