package me.henry.betterme.betterme.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.henry.betterme.betterme.R;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.edtPhoneNum)
    EditText edtPhoneNum;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.edtCode)
    EditText edtCode;
    @BindView(R.id.edtUserName)
    EditText edtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        btnRegister.setOnClickListener(this);
        tvGetCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                break;
            case R.id.tvGetCode:
                break;

        }
    }
}
