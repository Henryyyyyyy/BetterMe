package me.henry.betterme.betterme.demo.websockett;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.henry.betterme.betterme.R;




public class WebSocketActivity extends AppCompatActivity {
    String url="ws://192.168.11.105:8765";
    int port=8765;
@BindView(R.id.connect)
    TextView connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);
        ButterKnife.bind(this);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    WsClient c = WsClient.create();
                    c.connect();


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("tadi","exception="+e.toString());
                }
            }
        });

    }
}
