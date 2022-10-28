package cn.itcast.zhishang;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);

    }
}