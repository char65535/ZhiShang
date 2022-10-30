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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.itcast.zhishang.bean.Person;
import cn.itcast.zhishang.sql.SQLService;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener {
    private TextView title1, title2;
    private EditText username, email, pwd, rePwd;
    private ImageView usernamePhoto, pwdPhoto, emailPhoto, rePwdPhoto, register;
    private Button skipLogin;
    SQLService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        initView();
        service = new SQLService(getApplicationContext());
        title1.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
        title2.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
//        获取由LoginPage传来的数据
        getLoginData();
        skipLogin.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void getLoginData() {
        Bundle bundle = getIntent().getExtras();
        String usernameLoginData = bundle.getString("username");
        String pwdLoginData = bundle.getString("pwd");
        username.setText(usernameLoginData);
        pwd.setText(pwdLoginData);
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
        String nameText = username.getText().toString();
        String emailText = email.getText().toString();
        String pwdText = pwd.getText().toString();
        String rePwdText = rePwd.getText().toString();
        Person person = null;
        switch (v.getId()) {
            case R.id.skipLogin:
                Intent intent = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register:
                if (nameText.isEmpty() || pwdText.isEmpty()) {
                    Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rePwdText.isEmpty()) {
                    Toast.makeText(this, "请输入确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                person = new Person(nameText, emailText, pwdText, rePwdText);
                long rowId = service.addInfo(person);
                if (rowId != -1) {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("username", nameText);
                    bundle.putString("pwd", pwdText);
                    intent1.putExtras(bundle);
                    setResult(RESULT_OK, intent1);
                    finish();
                } else {
                    Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}












