package me.henry.scrollads;

import me.henry.scrollads.httpserver.myservers.FileType;
import me.henry.scrollads.httpserver.myservers.ResourceType;

/**
 * Created by zj on 2017/7/21.
 * me.henry.scrollads
 */

public class Advert {
    public String path;
    public FileType type;//0:picture,,1:视频,-1:unknown
    public String createTime;
    public int res;
    public String name;//测试用的
    public ResourceType fromType=ResourceType.File;//判断广告资源来源


}
