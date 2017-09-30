package me.henry.scrollads;

import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.support.annotation.RawRes;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.henry.scrollads.httpserver.myservers.FileType;
import me.henry.scrollads.httpserver.myservers.ResourceType;
import me.henry.scrollads.utils.ABFileUtil;

/**
 * Created by zj on 2017/7/21.
 * me.henry.scrollads
 */

public final class ScrollAdsConfig {
    public static final String TAG="ScrollAdsConfig";
    public static final int DELAY_TIME=5000;
    public static final int MaxCount=Integer.MAX_VALUE;
    public static final String FILE_DIR= Environment.getExternalStorageDirectory().toString()+File.separator+"AdverFiles";
    public static Sequence sequence=Sequence.Clockwise;
    public enum Sequence {
        Clockwise,AntiClockwise

    }
    public static final List<Advert> getFileToAds(@DrawableRes int[] drawArray, @RawRes int... rawArray){
        File[] fileList = ABFileUtil.getFileList("/AdverFiles");

        if (fileList.length==0){//如果filelist为空的时候，就用默认图片
            List<Advert>defaultAdslist=new ArrayList<>();
            Advert defAds;
            if (drawArray!=null){
                for (int i = 0; i < drawArray.length; i++) {
                    defAds=new Advert();
                    defAds.type= FileType.PICTURE;
                    defAds.res=drawArray[i];
                    defAds.fromType= ResourceType.RES;
                    defaultAdslist.add(defAds);
                }
            }
            if (rawArray!=null){
                for (int i = 0; i < rawArray.length; i++) {
                    defAds=new Advert();
                    defAds.type=FileType.VIDEO;
                    defAds.res=rawArray[i];
                    defAds.fromType= ResourceType.RES;
                    defaultAdslist.add(defAds);
                }
            }

            return defaultAdslist;
        }
        List<Advert>adslist=new ArrayList<>();
        Advert advert;
        String type;
        for (int i = 0; i < fileList.length; i++) {
            advert=new Advert();
            advert.path=fileList[i].getPath();
            type=fileList[i].getName().substring(fileList[i].getName().lastIndexOf(".") + 1,fileList[i].getName().length()).toLowerCase();
           if (type.equals("png")||type.equals("jpg")){
               advert.type=FileType.PICTURE;
           }else if (type.equals("mp4")){
               advert.type=FileType.VIDEO;
           }else {
               advert.type=FileType.UNKNOWN;
           }
            Log.e("qqaz","file name="+fileList[i]);
            adslist.add(advert);
        }
        return adslist;
    }
}
