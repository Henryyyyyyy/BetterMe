package me.henry.scrollads.httpserver.model;

import me.henry.scrollads.utils.GsonUtil;

/**
 * Created by zj on 2017/8/2.
 * me.henry.scrollads.httpserver.model
 */

public class LoginModel {
    String type;//change,login
    String account;
    String password;
    String newAccount;
    String newPassword;

    public LoginModel(String type,String account, String newAccount, String password, String newPassword) {
        this.type=type;
        this.account = account;
        this.newAccount = newAccount;
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(String newAccount) {
        this.newAccount = newAccount;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public static LoginModel parseJson(String json){
       return GsonUtil.GsonToBean(json, LoginModel.class);
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "type='" + type + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", newAccount='" + newAccount + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
