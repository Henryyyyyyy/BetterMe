package me.henry.betterme.betterme.demo.colorchange;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.henry.betterme.betterme.R;

public class ColorChangeActivity extends AppCompatActivity {
@BindView(R.id.rlShow)
    RelativeLayout rlShow;
    @BindView(R.id.btnChange)
    Button btnChange;
    @BindView(R.id.edtColor)
    EditText edtColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_change);
        ButterKnife.bind(this);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color=edtColor.getText().toString();
                rlShow.setBackgroundColor(blendColor(Color.parseColor(color),Color.WHITE));
            }
        });
    }
    public int blendColor(int fg, int bg) {
        int scr = Color.red(fg);
        int scg = Color.green(fg);
        int scb = Color.blue(fg);
        int sa = fg >>> 24;
        int dcr = Color.red(bg);
        int dcg = Color.green(bg);
        int dcb = Color.blue(bg);
        int color_r = dcr * (0xff - sa) / 0xff + scr * sa / 0xff;
        int color_g = dcg * (0xff - sa) / 0xff + scg * sa / 0xff;
        int color_b = dcb * (0xff - sa) / 0xff + scb * sa / 0xff;
        return ((color_r << 16) + (color_g << 8) + color_b) | (0xff000000);
    }
}
