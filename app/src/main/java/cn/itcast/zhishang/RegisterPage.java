package cn.itcast.zhishang;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener {
    private TextView title1, title2;
    private EditText username, email, pwd, rePwd;
    private ImageView usernamePhoto, pwdPhoto, emailPhoto, rePwdPhoto, register;
    private Button skipLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        initView();
        title1.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
        title2.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
//        获取由LoginPage传来的数据
        Bundle bundle = getIntent().getExtras();
        String usernameData = bundle.getString("username");
        String pwdData = bundle.getString("pwd");
        username.setText(usernameData);
        pwd.setText(pwdData);
        skipLogin.setOnClickListener(this);


    }

    private void initView() {
        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        rePwd = findViewById(R.id.rePwd);
        usernamePhoto = findViewById(R.id.usernamePhono);
        pwdPhoto = findViewById(R.id.pwdPhoto);
        emailPhoto = findViewById(R.id.emailPhoto);
        rePwdPhoto = findViewById(R.id.rePwdPhoto);
        register = findViewById(R.id.register);
        skipLogin = findViewById(R.id.skipLogin);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(RegisterPage.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}