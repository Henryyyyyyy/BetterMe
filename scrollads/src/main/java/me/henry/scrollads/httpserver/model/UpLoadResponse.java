package me.henry.scrollads.httpserver.model;

import me.henry.scrollads.utils.GsonUtil;

/**
 * Created by zj on 2017/8/2.
 * me.henry.scrollads.httpserver.model
 */

public class UpLoadResponse {
    int code;
    String msg;

    public UpLoadResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
    public String createResponse(){
        return GsonUtil.GsonString(this);
    }
}
