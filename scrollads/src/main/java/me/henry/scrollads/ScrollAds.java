package me.henry.scrollads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import me.henry.scrollads.httpserver.myservers.FileType;
import me.henry.scrollads.httpserver.myservers.ResourceType;

/**
 * Created by zj on 2017/7/21.
 * me.henry.scrollads
 * //        int mid = Integer.MAX_VALUE/2;//把初始位置放到中间,用于优化做的，但是没必要
 //        setCurrentItem(mid - mid/adverts.size());
 * 未解决的bug:viewpager有可能滑完
 */

public final class ScrollAds extends NoscrollViewPager implements AdverAdapter.OnKnowCurItem {
    private List<Advert> adverts;
    private int mDelayTime = ScrollAdsConfig.DELAY_TIME;
    private int mFlipSpeed = 600;
    private AdverAdapter mAdapter;
    private ScrollHandler mHandler;
private Context mContext;
    public ScrollAds(Context context) {
        this(context, null);
    }

    public ScrollAds(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        adverts = new ArrayList<>();
        mAdapter = new AdverAdapter(context, this);
        mAdapter.setOnKnownCurItem(this);
        mHandler = new ScrollHandler(new WeakReference<Activity>((Activity) context), this);
        adjustSpeed();
        setAdapter(mAdapter);
    }


    public ScrollAds setAdverts(List<Advert> ads) {
        this.adverts = ads;
        mAdapter.setData(adverts);
        return this;
    }

    public ScrollAds setDelayTime(int time) {
        this.mDelayTime = time;
        mHandler.setDelayTime(time);
        return this;
    }

    public ScrollAds setFlipSpeed(int speed) {
        this.mFlipSpeed = speed;
        return this;
    }

    public ScrollAds start() {
        setListener();
        mAdapter.notifyDataSetChanged();
        if (adverts.get(0).type!=FileType.VIDEO) {//不是视频，就开始更新轮播
            sendUpdateMsg(ScrollHandler.MSG_UPDATE);
        }
        return this;
    }

    int curPos = 0;
    View curView;
    Uri uri;
    MyVideoView myVideoView;
    ImageView myImageView;
    Advert currentAdvert;
    private void setListener() {
        this.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("wsx", "onPageSelected-----------------------=" + position);
                curPos = position;
                currentAdvert = adverts.get(getRealPostion(position, adverts.size()));
                if (currentAdvert.type != FileType.VIDEO) {
                    sendUpdateMsg(ScrollHandler.MSG_UPDATE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        myImageView = (ImageView) curView.findViewById(R.id.contentImage);
                        myVideoView = (MyVideoView) curView.findViewById(R.id.contentVideo);
                        if (currentAdvert.type == FileType.VIDEO) {
                            if (currentAdvert.fromType== ResourceType.RES){
                                //// TODO: 2017/8/29 这里还没有验证行不行 ,我觉得可以。
                                uri=Uri.parse("android.resource://" + mContext.getPackageName() + "/" +currentAdvert.res);
                            }else {
                                uri = Uri.parse(currentAdvert.path);

                            }
                            setVideo();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public  void adjustSpeed() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(),
                    new AccelerateInterpolator());
            field.set(this, scroller);
            scroller.setmDuration(mFlipSpeed);
        } catch (Exception e) {
        }
    }

    public void sendUpdateMsg(int mType) {
        Message msg = new Message();
        msg.what = mType;
        msg.obj = getCurrentItem();
        Log.e("wsx", "send getCurrentItem-------" + getCurrentItem());
        mHandler.sendMessageDelayed(msg, mDelayTime);
    }

    private int getRealPostion(int position, int size) {
        int real = position % size;
        if (real < 0) {
            real = size + real;
        }
        return real;
    }



    @Override
    public void onThisItem(int position, Object obj) {
        curView = (View) obj;
        if (position == 0 && adverts.get(0).type == FileType.VIDEO) {//第一个是视频的时候
            uri = Uri.parse(adverts.get(0).path);
            setVideo();
        }
    }

    public void setVideo() {
        myVideoView = (MyVideoView) curView.findViewById(R.id.contentVideo);
        myImageView = (ImageView) curView.findViewById(R.id.contentImage);
        myVideoView.setVideoURI(uri);
        myVideoView.start();
        // myVideoView.requestFocus();
        myVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                sendUpdateMsg(ScrollHandler.MSG_UPDATE);
                new Handler().postDelayed(new Runnable() {//处理图片显示的一些奇怪问题,要先把imageview显示出来
                    @Override
                    public void run() {
                        // TODO: 2017/8/29 这里可以试试将videoview设为stop
                        myImageView.setVisibility(VISIBLE);
                        myImageView.setBackgroundColor(Color.BLACK);
                        myVideoView.setVisibility(GONE);
                    }
                }, mDelayTime - 200);

            }
        });
    }
}
