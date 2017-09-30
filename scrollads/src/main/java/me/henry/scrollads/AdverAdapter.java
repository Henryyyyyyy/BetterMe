package me.henry.scrollads;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import me.henry.scrollads.httpserver.myservers.FileType;
import me.henry.scrollads.httpserver.myservers.ResourceType;
import me.henry.scrollads.utils.ABFileUtil;

/**
 * Created by zj on 2017/7/24.
 * me.henry.scrollads
 */

public class AdverAdapter extends PagerAdapter{
    private List<View> mViews=new ArrayList<>();
    private List<Advert> mAdvert=new ArrayList<>();
    private Context mContext;
    private ScrollAds mPager;
    public OnKnowCurItem mOnKnowCurItem;


    public AdverAdapter(Context c,ScrollAds pager) {
        mContext=c;
        mPager=pager;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)   {
        //container.removeView(mViews.get(position));//删除页卡
    }
    @Override
    public int getCount() {
        return  ScrollAdsConfig.MaxCount;//返回页卡的数量
    }



    Bitmap resBitmap;
    View v;
    ImageView iv;
    MyVideoView video;
    Advert adver;
    ViewParent vp;
    ViewGroup parent;
    @Override
    public Object instantiateItem(ViewGroup container,  int position) {
        //这个方法用来实例化页卡
        position %= mViews.size();
        if (position<0){
            position = mViews.size()+position;
        }
         v=mViews.get(position);
         iv= (ImageView) v.findViewById(R.id.contentImage);
         video= (MyVideoView) v.findViewById(R.id.contentVideo);
         adver=mAdvert.get(position);
        if (adver.type== FileType.PICTURE){//图片
            iv.setVisibility(View.VISIBLE);
            video.setVisibility(View.GONE);
        }if (adver.type==FileType.VIDEO){//视频
            iv.setVisibility(View.GONE);
            video.setVisibility(View.VISIBLE);
        }
        if (adver.type==FileType.PICTURE){
            if (adver.fromType== ResourceType.RES){//如果是本地图片的话
                iv.setImageResource(adver.res);
            }else {//文件加载图片
                resBitmap = ABFileUtil.convertFileToBitmap(adver.path);
                iv.setImageBitmap(resBitmap);

            }

        }
//// TODO: 2017/8/29 解析本地视频或者file视频 
         vp =v.getParent();//一定要加下面这些代码去removeview，不然会报错
        if (vp!=null){
             parent = (ViewGroup)vp;
            parent.removeView(v);
        }
        container.addView(v);

        return v;
    }

int lastPos=-1;
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (position!=lastPos){
            mOnKnowCurItem.onThisItem(position,object);
            Log.e("wsx","setPrimaryItem="+position);
            lastPos=position;
        }
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }
    public void setData(List<Advert> ads){
        mViews.clear();
        mAdvert.clear();
        mAdvert=ads;
        if (mAdvert.size()==2){//解决因viewpager机制而产生的问题
            mAdvert.add(mAdvert.get(0));
            mAdvert.add(mAdvert.get(1));
        }if (mAdvert.size()==1){
            mAdvert.add(mAdvert.get(0));
            mAdvert.add(mAdvert.get(0));
        }
        View view;
        LayoutInflater lf = LayoutInflater.from(mContext);
        for (int i = 0; i < mAdvert.size(); i++) {
            view = lf.inflate(R.layout.page_ads, null);
            mViews.add(view);
        }
    }

   public interface OnKnowCurItem{
       void onThisItem(int position,Object obj);
   }
    public void setOnKnownCurItem(OnKnowCurItem listener){
        this.mOnKnowCurItem=listener;
    }
}
