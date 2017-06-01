package me.henry.betterme.betterme.http.callback;


import me.henry.betterme.betterme.http.AppException;

/**
 * Created by zj on 2017/5/26.
 * me.henry.betterme.betterme.http
 * 如果抽象类已经实现了接口中的某个方法，那么继承这个抽象类后，就不用再实现已经在抽象类实现的方法了
 */

public abstract class FileCallBack extends AbstractCallBack<String> {
    @Override
    protected String bindData(String result) throws AppException {

   return result;

    }
}
