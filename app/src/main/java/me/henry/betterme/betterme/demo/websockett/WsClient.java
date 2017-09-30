package me.henry.betterme.betterme.demo.websockett;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zj on 2017/9/29.
 * me.henry.betterme.betterme.demo.websockett
 */

public class WsClient extends WebSocketClient {
    private WsClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
    }

    public static WsClient create() throws URISyntaxException {
        Map<String, String> headers = new HashMap<>();
        headers.put("sn", "11228899778888");
        WsClient client = new WsClient(new URI("ws://192.168.11.121:8765"), new Draft_6455(), headers, 0);
        return client;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

    }

    @Override
    public void onMessage(String message) {
        Log.e("taddd","message="+message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }
}
