package me.henry.betterme.betterme.http;


/**
 *用于全局判断，例如如果所有操作都要获得token，
 * 如果搜索到token过期，要重新登录的话，request就要设置这个方法.
 */
public interface OnGlobalExceptionListener {

    boolean handleException(AppException exception);
}
