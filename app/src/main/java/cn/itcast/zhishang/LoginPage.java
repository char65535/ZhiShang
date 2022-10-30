package cn.itcast.zhishang;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import cn.itcast.zhishang.bean.Person;
import cn.itcast.zhishang.sql.SQLService;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    private EditText username, pwd;
    private ImageView usernamePhoto, pwdPhoto, skipLogin;
    private Button skipRegister;
    private CheckBox clause;
    private ActivityResultLauncher<Intent> launcher;
    private String RegisterUsername, RegisterPwd;
    private String usernameText;
    private String pwdText;
    private String personName;
    SQLService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        service = new SQLService(getApplicationContext());
        initView();
        usernamePhoto.setOnClickListener(this);
        pwdPhoto.setOnClickListener(this);
        skipRegister.setOnClickListener(this);
        skipLogin.setOnClickListener(this);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getData() != null && result.getResultCode() == RESULT_OK) {
                    RegisterUsername = result.getData().getStringExtra("username");
                    RegisterPwd = result.getData().getStringExtra("pwd");
                    username.setText(RegisterUsername);
                    pwd.setText(RegisterPwd);
                }
            }
        });
    }

    private void initView() {
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
        usernamePhoto = findViewById(R.id.usernamePhono);
        pwdPhoto = findViewById(R.id.pwdPhoto);
        skipRegister = findViewById(R.id.skipRegister);
        skipLogin = findViewById(R.id.skipLogin);
        clause = findViewById(R.id.clause);
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
        usernameText = username.getText().toString();
        pwdText = pwd.getText().toString();

        if (!TextUtils.isEmpty(usernameText) && !TextUtils.isEmpty(pwdText)) {
            ArrayList<Person> data = service.getAllData();
            boolean match = false;
            boolean match2 = false;
            for (int i = 0; i < data.size(); i++) {
                Person person = data.get(i);
                if ((usernameText.equals(person.getName())) && pwdText.equals(person.getPwd()) ||
                        (usernameText.equals(person.getEmail())) && pwdText.equals(person.getPwd())) {
                    personName = person.getName();
                    match = true;
                    break;
                } else {
                    match = false;
                }
            }
            if (match) {
                if (!clause.isChecked()) {
                    Toast.makeText(this, "请阅读并同意《服务协议》和《用户政策》", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                Runnable target;
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(2000);//2秒 模拟登录时间
                            String user_name = personName;
                            Intent intent1 = new Intent(LoginPage.this, FirstActivity.class);//设置自己跳转到成功的界面
                            //intent1.putExtra("user_name",user_name);
                            startActivity(intent1);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();//打开线程

            } else {
                Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
        }

//        if (usernameText.isEmpty() || pwdText.isEmpty()) {
//            Toast.makeText(LoginPage.this, "用户名和密码不能为空",
//                    Toast.LENGTH_LONG).show();
//            return;
//        }
//        if (!clause.isChecked()) {
//            Toast.makeText(LoginPage.this, "您需要同意用户隐私协议",
//                    Toast.LENGTH_LONG).show();
//            return;
//        }
//        Toast.makeText(LoginPage.this, "登录成功", Toast.LENGTH_SHORT).show();
    }


    private void register() {
        usernameText = username.getText().toString();
        pwdText = pwd.getText().toString();
        Intent intentRegister = new Intent();
        intentRegister.setClass(LoginPage.this, RegisterPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", usernameText);
        bundle.putString("pwd", pwdText);
        intentRegister.putExtras(bundle);
        launcher.launch(intentRegister);
    }
}













