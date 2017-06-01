package me.henry.betterme.betterme.http;

/**
 * Created by zj on 2017/5/31.
 * me.henry.betterme.betterme.http
 */

public class AppException extends Exception{
    public int statusCode;
    public String responseMessage;

    public enum ErrorType {CANCEL,TIMEOUT, SERVER, JSON, IO, FILE_NOT_FOUND, UPLOAD, MANUAL}
    public ErrorType type;

    public AppException(int status, String responseMessage) {
        super(responseMessage);
        this.type = ErrorType.SERVER;
        this.statusCode = status;
        this.responseMessage = responseMessage;
    }

    public AppException(ErrorType type,String detailMessage) {
        super(detailMessage);
        this.type = type;
    }
}
