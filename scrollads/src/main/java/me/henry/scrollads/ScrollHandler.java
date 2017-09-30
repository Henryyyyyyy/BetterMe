package me.henry.scrollads;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

import static me.henry.scrollads.ScrollAdsConfig.MaxCount;

/**
 * Created by zj on 2017/7/24.
 * me.henry.scrollads
 */

public class ScrollHandler extends Handler{
    private WeakReference<Activity> weakReference;
    private ScrollAds mScrollAds;
    public static final int MSG_UPDATE  = 1;
    public static final int MSG_KEEP_SILENT   = 2;
    private int mDelayTime = ScrollAdsConfig.DELAY_TIME;
    private int scrollMaxItem=MaxCount-100;
    private int currentItem=0;
    protected ScrollHandler(WeakReference<Activity> wk,ScrollAds scrollAds){
        weakReference = wk;
        mScrollAds=scrollAds;
    }
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Activity activity = weakReference.get();
        if (activity==null){

            //Activity已经回收，无需再处理UI了
            return ;
        }
        //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。主要是处理第一次过后还存在这条消息的话(刚开始不会触发pagselect)，就要移除
//        if (this.hasMessages(MSG_UPDATE)){
//            Log.e("wsx","hasMessages----------------------");
//            this.removeMessages(MSG_UPDATE);
//        }
        switch (msg.what) {
            case MSG_UPDATE:
                currentItem=((int)msg.obj);
                if (currentItem==0){
                    mScrollAds.setCurrentItem(currentItem+1);
                    ScrollAdsConfig.sequence= ScrollAdsConfig.Sequence.Clockwise;
                }
                if (currentItem>0&&currentItem<scrollMaxItem&& ScrollAdsConfig.sequence==ScrollAdsConfig.Sequence.Clockwise){
                    mScrollAds.setCurrentItem(currentItem+1);
                }
                if (currentItem>0&&currentItem<scrollMaxItem&& ScrollAdsConfig.sequence==ScrollAdsConfig.Sequence.AntiClockwise){
                    mScrollAds.setCurrentItem(currentItem-1);
                }
                if (currentItem>=scrollMaxItem){
                    mScrollAds.setCurrentItem(currentItem-1);
                    ScrollAdsConfig.sequence= ScrollAdsConfig.Sequence.AntiClockwise;
                }




//                if (currentItem>10){
//                    mScrollAds.setCurrentItem(currentItem-1);
//                }else {
//                    mScrollAds.setCurrentItem(currentItem+1);
//                }

                //准备下次播放
                break;
            case MSG_KEEP_SILENT:

                break;
            default:
                break;
        }

    }

    public void setDelayTime(int delayTime) {
        this.mDelayTime = delayTime;
    }
}
