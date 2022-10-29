package cn.itcast.zhishang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    private EditText username, pwd;
    private ImageView usernamePhoto, pwdPhoto, skipLogin;
    private Button skipRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        initView();
        usernamePhoto.setOnClickListener(this);
        pwdPhoto.setOnClickListener(this);
        skipRegister.setOnClickListener(this);
        skipLogin.setOnClickListener(this);
    }

    private void initView() {
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
        usernamePhoto = findViewById(R.id.usernamePhono);
        pwdPhoto = findViewById(R.id.pwdPhoto);
        skipRegister = findViewById(R.id.skipRegister);
        skipLogin = findViewById(R.id.skipLogin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.usernamePhono:
                Toast.makeText(LoginPage.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pwdPhoto:
                Toast.makeText(LoginPage.this, "请输入密码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.skipLogin:
                login();
                break;
            case R.id.skipRegister:
                register();
                break;

        }

    }

    private void login() {
        Toast.makeText(LoginPage.this, "点击了登录按钮", Toast.LENGTH_SHORT).show();
    }

    private void register() {
        String usernameText = username.getText().toString();
        String pwdText = pwd.getText().toString();
        Intent intentRegister = new Intent();
        intentRegister.setClass(LoginPage.this, RegisterPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", usernameText);
        bundle.putString("pwd", pwdText);
        intentRegister.putExtras(bundle);
        startActivity(intentRegister);
    }
}