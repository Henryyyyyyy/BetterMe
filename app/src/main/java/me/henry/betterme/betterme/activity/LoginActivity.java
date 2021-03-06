package me.henry.betterme.betterme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import butterknife.BindView;
import butterknife.ButterKnife;
import me.henry.betterme.betterme.R;
import me.henry.betterme.betterme.common.MyConstants;
import me.henry.betterme.betterme.utils.SharedPresUtil;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.etPhoneNum)
    EditText etPhoneNum;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:

                break;
            case R.id.btnRegister:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
