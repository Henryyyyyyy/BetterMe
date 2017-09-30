package me.henry.scrollads.httpserver.model;

import me.henry.scrollads.utils.GsonUtil;

/**
 * Created by zj on 2017/8/2.
 * me.henry.scrollads.httpserver.model
 */

public class MyResponse {
    int code;
    String msg;
    String token;

    public MyResponse(int code, String msg, String token) {
        //200成功,400失败，500未知错误
        this.code = code;
        this.msg = msg;
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String createResponse(){
        return GsonUtil.GsonString(this);
    }
}
