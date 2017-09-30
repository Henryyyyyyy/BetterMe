package me.henry.scrollads.httpserver.myservers;

import android.util.Log;

import java.io.IOException;

import me.henry.scrollads.httpserver.nanos.IHTTPSession;
import me.henry.scrollads.httpserver.nanos.response.Response;
import me.henry.scrollads.httpserver.websk.CloseCode;
import me.henry.scrollads.httpserver.websk.NanoWSD;
import me.henry.scrollads.httpserver.websk.WebSocket;
import me.henry.scrollads.httpserver.websk.WebSocketFrame;

import static android.R.attr.port;

/**
 * Created by zj on 2017/7/31.
 * me.henry.scrollads.httpserver.myservers
 */

public class SevenWebSocketServer extends NanoWSD{
    public SevenWebSocketServer(String adress,int port) {
        super(adress,port);
    }

    @Override
    protected WebSocket openWebSocket(IHTTPSession handshake) {
        return new MyWebSocket(handshake);
    }

    @Override
    protected Response serve(IHTTPSession session) {
//        Log.e("wsx","has respond");
//        Log.e("wsx","session.getUri()="+session.getUri());
//        Log.e("wsx","session.getMethod()="+session.getMethod());
//        StringBuilder builder = new StringBuilder();
//        builder.append("<!DOCTYPE html><html><body>");
//        builder.append("404 -- Sorry, Can't Foundssssssssss "+ session.getUri() + " !");
//        builder.append("</body></html>\n");


        StringBuilder builder = new StringBuilder();
        builder.append("cao ni");
        return Response.newFixedLengthResponse(builder.toString());
    }
    public static  class MyWebSocket extends WebSocket{

        public MyWebSocket(IHTTPSession handshakeRequest) {
            super(handshakeRequest);
        }

        @Override
        protected void onOpen() {

        }

        @Override
        protected void onClose(CloseCode code, String reason, boolean initiatedByRemote) {

        }

        @Override
        protected void onMessage(WebSocketFrame message) {

        }

        @Override
        protected void onPong(WebSocketFrame pong) {

        }

        @Override
        protected void onException(IOException exception) {

        }
    }
}
