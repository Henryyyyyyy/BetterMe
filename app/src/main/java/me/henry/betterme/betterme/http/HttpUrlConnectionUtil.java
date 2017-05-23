package me.henry.betterme.betterme.http;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by zj on 2017/5/23.
 * me.henry.betterme.betterme.http
 */

public class HttpUrlConnectionUtil {
    public static String execute(Request request) throws IOException {

        switch (request.method) {
            case GET:
            case DELETE:
                return get(request);
            case POST:
            case PUT:
                return post(request);
        }

        return null;
    }


    /**
     * 请求其实就分几个步骤，
     * 获得一个url的httpurlconnection,然后一些connection的参数，
     */
    private static String get(Request request) throws IOException {

        HttpURLConnection connection = null;


        connection = (HttpURLConnection) new URL(request.url).openConnection();
        connection.setRequestMethod(request.method.name());
        connection.setConnectTimeout(15 * 3000);
        connection.setReadTimeout(15 * 3000);
        //以上配置完成

        int status=connection.getResponseCode();
        if (status==200){
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            InputStream is=connection.getInputStream();
            byte[]buffer=new byte[2048];
            int len;
            while ((len=is.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            out.flush();
            out.close();
            return  new String(out.toByteArray());
        }
        return null;

    }


    private static String post(Request request) throws IOException {
        HttpURLConnection connection = null;
        OutputStream os = null;

            connection = (HttpURLConnection) new URL(request.url).openConnection();
            connection.setRequestMethod(request.method.name());
            connection.setConnectTimeout(15 * 3000);
            connection.setReadTimeout(15 * 3000);
            connection.setDoOutput(true);
            addHeader(connection, request.headers);
            //以上配置完成
            os = connection.getOutputStream();
            if (request.content != null) {
                os.write(request.content.getBytes());
            }
            int status=connection.getResponseCode();
            if (status==200){
                ByteArrayOutputStream out=new ByteArrayOutputStream();
                InputStream is=connection.getInputStream();
                byte[]buffer=new byte[2048];
                int len;
                while ((len=is.read(buffer))!=-1){
                    out.write(buffer,0,len);
                }
                os.flush();
                os.close();
                return  new String(out.toByteArray());
            }
        return null;
    }

    private static void addHeader(HttpURLConnection connection, Map<String, String> headers) {
        if (headers == null || headers.size() == 0)
            return;

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            connection.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }
}
