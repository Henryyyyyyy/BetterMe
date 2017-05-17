package me.henry.swieepsdk.http.core;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.net.sip.SipErrorCode.TIME_OUT;

/**
 * Created by zj on 2017/3/3.
 * me.henry.swieepsdk.http
 * 发送请求
 */

public class SwieepHttpClient {
    private static final int TimeOut=30;//超时时间
    private static OkHttpClient  mSwieepHttpClient;
    static {

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        //主要用于https
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        /**
         *  为所有请求添加请求头，看个人需求
         */
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("User-Agent", "Imooc-Mobile") // 标明发送本次请求的客户端
                        .build();
                return chain.proceed(request);
            }
        });
        //okHttpClientBuilder.cookieJar(new SimpleCookieJar());
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true);
        /**
         * trust all the https point
         */
        okHttpClientBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        mSwieepHttpClient = okHttpClientBuilder.build();
    }
    public static OkHttpClient getSwieepHttpClient() {
        return mSwieepHttpClient;
    }


}
