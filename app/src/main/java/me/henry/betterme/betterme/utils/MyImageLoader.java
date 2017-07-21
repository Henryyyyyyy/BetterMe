package me.henry.betterme.betterme.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by zj on 2017/7/20.
 * me.henry.betterme.betterme.utils
 */
public class MyImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .crossFade()
                .into(imageView);
    }
}

