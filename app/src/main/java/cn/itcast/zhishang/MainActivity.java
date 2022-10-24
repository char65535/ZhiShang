package cn.itcast.zhishang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView, version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        检测至尚软件是否打开
        Toast.makeText(this, "欢迎使用至尚笔记", Toast.LENGTH_SHORT).show();
//        实现页面全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
//        更改textView处文字字体
        textView.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
//        实现数据回传 ->version
        registerForActivityResult(new );

        //跳转到登录页面
        Intent intent = new Intent();
        intent.setClass(this, LoginPage.class);

    }

    private void initView() {
        textView = findViewById(R.id.textView);
        version = findViewById(R.id.version);
    }
}