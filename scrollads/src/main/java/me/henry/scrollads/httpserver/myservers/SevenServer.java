package me.henry.scrollads.httpserver.myservers;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.henry.scrollads.ScrollAdsConfig;
import me.henry.scrollads.httpserver.model.LoginModel;
import me.henry.scrollads.httpserver.model.MyResponse;
import me.henry.scrollads.httpserver.nanos.HTTPSession;
import me.henry.scrollads.httpserver.nanos.IHTTPSession;
import me.henry.scrollads.httpserver.nanos.NanoHTTPD;
import me.henry.scrollads.httpserver.nanos.request.Method;
import me.henry.scrollads.httpserver.nanos.response.Response;
import me.henry.scrollads.httpserver.nanos.response.Status;
import me.henry.scrollads.utils.ABIOUtil;
import me.henry.scrollads.utils.ABTextUtil;
import me.henry.scrollads.utils.CloudPresUtil;
import me.henry.scrollads.utils.MD5;

/**
 * Created by zj on 2017/7/25.
 * me.henry.scrollads.httpserver
 */

public final class SevenServer extends NanoHTTPD {
    public static final String TAG = "SevenServer";
    public String uri;
    public Method method;
    public Context mContext;
    public Map<String, String> headers;
    public String mToken = "";

    public SevenServer(int port, Context context) {
        super(port);
        mContext = context;
    }


    @Override
    protected Response serve(IHTTPSession session) {
        uri = session.getUri();
        method = session.getMethod();
        headers = session.getHeaders();
        if (headers.containsKey("token")) {//获取token
            mToken = headers.get("token");
            Log.e(TAG, "token=" + mToken);
        }else {
            mToken="";
        }
        Log.e(TAG, "serve....uri=" + uri + "   method=" + method + "   token=" + mToken);
        if (uri.equals("/updateAdverts") && Method.POST.equals(method)) {
            if (mToken.equals(CloudPresUtil.getString(mContext,ServerConfig.Pref_Token))){
                return handlerUpdateAdverts(session);
            }else {
                return getResponse(new MyResponse(400, "token有误", "").createResponse());
            }
        }
        if (uri.equals("/login") && Method.POST.equals(method)) {
            return handlerLogin(session);
        }


        return getResponse(new MyResponse(400, "没有合适的uri", "").createResponse());
    }

    /**
     * @param session
     * @return
     */
    public Response handlerLogin(IHTTPSession session) {
        try {
            String localAccount = CloudPresUtil.getString(mContext, ServerConfig.Pref_Account);
            String localPassword = CloudPresUtil.getString(mContext, ServerConfig.Pref_Password);
            Map<String, String> mf = new HashMap<>();
            session.parseBody(mf);
            String body = mf.get(HTTPSession.POST_DATA);
            LoginModel loginModel = LoginModel.parseJson(body);
            String type = loginModel.getType();
            if (type.equals("login")) {//登录处理
                if (localAccount.equals(ServerConfig.Default_Account) && localPassword.equals(ServerConfig.Default_Password)) {
                    return getResponse(new MyResponse(400, "首次登录必须采用change类型修改账号密码", "").createResponse());
                }
                Log.e(TAG, "进入登录处理....");
                if (localAccount.equals(loginModel.getAccount()) && localPassword.equals(loginModel.getPassword())) {
                    String token = MD5.GetMD5Code(loginModel.getAccount() + loginModel.getPassword() + System.currentTimeMillis());
                    CloudPresUtil.setString(mContext, ServerConfig.Pref_Token, token);
                    return getResponse(new MyResponse(200, "登录成功", token).createResponse());
                } else {
                    //密码错误
                    Log.e(TAG, "密码错误....");
                    return getResponse(new MyResponse(400, "密码错误", "").createResponse());
                }

            } else if (type.equals("change")) {//改密码处理
                boolean isEmptyNewAccount = ABTextUtil.isEmpty(loginModel.getNewAccount());
                boolean isEmptyNewPassword = ABTextUtil.isEmpty(loginModel.getNewPassword());
                boolean isEqualAccount = localAccount.equals(loginModel.getAccount());
                boolean isEqualPassword = localPassword.equals(loginModel.getPassword());
                if (!isEmptyNewAccount && !isEmptyNewPassword && isEqualAccount && isEqualPassword) {
                    CloudPresUtil.setString(mContext, ServerConfig.Pref_Account, loginModel.getNewAccount());
                    CloudPresUtil.setString(mContext, ServerConfig.Pref_Password, loginModel.getNewPassword());
                    String token = MD5.GetMD5Code(loginModel.getNewAccount() + loginModel.getNewPassword() + System.currentTimeMillis());
                    CloudPresUtil.setString(mContext, ServerConfig.Pref_Token, token);
                    return getResponse(new MyResponse(200, "修改密码成功,登录成功", token).createResponse());
                } else {//错误的返回信息
                    return getResponse(new MyResponse(400, "账号或密码输入有误", "").createResponse());
                }
            } else {
                return getResponse(new MyResponse(500, "未知登录类型", "").createResponse());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getResponse(new MyResponse(500, "未知错误" + e.toString(), "").createResponse());
        }

    }

    public Response handlerUpdateAdverts(IHTTPSession session) {
        Method method = session.getMethod();
        // get file from body
        Map<String, String> files = new HashMap<>();
        if (Method.POST.equals(method) || Method.PUT.equals(method)) {
            try {
                session.parseBody(files);
            } catch (IOException ioe) {
                return getResponse(new MyResponse(400, "更新广告失败"+ioe.toString(), "").createResponse());
            } catch (ResponseException re) {
                return getResponse(new MyResponse(400, "更新广告失败"+re.getMessage(), "").createResponse());
            }
        }
        try {
            //copyfile
            Map<String, String> params = session.getParms();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String paramsKey = entry.getKey();
                String tmpFilePath = files.get(paramsKey);
                String fileName = adjustFileName(paramsKey);
                Log.e("suck", "paramsKey=" + fileName);
                File tmpFile = new File(tmpFilePath);
                File targetFile = new File(ScrollAdsConfig.FILE_DIR + File.separator + fileName);
                ABIOUtil.copyFile(tmpFile, targetFile);
            }
        } catch (Exception e) {
            Log.e("suck", "failed....=" + e.toString());
            return getResponse(new MyResponse(400, "更新广告失败"+e.toString(), "").createResponse());

        }

         return getResponse(new MyResponse(200, "更新广告成功", "").createResponse());
    }


    public Response getFailedResponse() {
        return Response.newFixedLengthResponse(Status.BAD_REQUEST, NanoHTTPD.MIME_PLAINTEXT, "bad request self");
    }

    public Response getSuccessResponse() {
        return Response.newFixedLengthResponse(Status.OK, NanoHTTPD.MIME_PLAINTEXT, "success");
    }

    public Response getResponse(Status status, String mimeType, String msg) {
        return Response.newFixedLengthResponse(status, mimeType, msg);
    }

    public Response getResponse(String msg) {
        return Response.newFixedLengthResponse(Status.BAD_REQUEST, NanoHTTPD.MIME_PLAINTEXT, msg);
    }

    /**
     * 校正文件名字，如果不用这个方法，文件名后面会出现下标
     *
     * @param name
     * @return
     */
    public String adjustFileName(String name) {
        String type;
        String realName;
        String[] str = name.split("\\.");
        type = str[1];
        if (type.contains("mp4")) {
            type = "mp4";
        }
        if (type.contains("png")) {
            type = "png";
        }
        if (type.contains("jpg")) {
            type = "jpg";
        }
        realName = str[0] + "." + type;
        return realName;
    }

}
