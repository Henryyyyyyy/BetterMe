package me.henry.betterme.betterme.http;

/**
 * Created by zj on 2017/5/26.
 * me.henry.betterme.betterme.http
 */

public class StayUser {
    public String id;
    public String account;
    public String email;
    public String username;
    public String token;

    @Override
    public String toString() {
        return "StayUser{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
