package me.henry.betterme.betterme.model;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.module.GlideModule;

import static android.R.attr.data;

/**
 * Created by zj on 2017/4/6.
 * me.henry.betterme.betterme.model
 */

public class ZengJinGlideModule implements GlideModule{
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //SDCard/Android/data/应用包名/cache/image_manager_disk_cache目录下
        //设置磁盘缓存大小
        int size = 100 * 1024 * 1024;
        String dir = "zengjinImgCache";
        //设置磁盘缓存
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,dir, size));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
