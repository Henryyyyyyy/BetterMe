package me.henry.scrollads;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zj on 2017/7/21.
 * me.henry.scrollads
 */

public class ScrollAds extends ViewPager{
private List<Advert> adverts;
private int delayTime=ScrollAdsConfig.DELAY_TIME;
    public ScrollAds(Context context) {
      this(context,null);
    }

    public ScrollAds(Context context, AttributeSet attrs) {
        super(context, attrs);
        adverts=new ArrayList<>();
    }
    public ScrollAds setAdverts(List<Advert> ads){
        this.adverts=ads;
        return this;
    }
    public ScrollAds setDelayTime(int time){
        this.delayTime=time;
        return this;
    }
}
