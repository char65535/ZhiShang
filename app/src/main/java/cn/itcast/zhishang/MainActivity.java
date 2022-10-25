package cn.itcast.zhishang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView textView, version;
    Button btn_skipPage;
    private int recLen = 5;
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        检测至尚软件是否打开
        Toast.makeText(this, "欢迎使用至尚笔记", Toast.LENGTH_SHORT).show();
//        实现页面全屏
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        timer.schedule(task, 1000, 1000);
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                redirect();
            }
        }, 5000);
        initView();
        btn_skipPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
            }
        });
//        更改textView处文字字体
        textView.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
    }

    private void redirect() {
        Intent intent = new Intent(MainActivity.this, LoginPage.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        textView = findViewById(R.id.textView);
        version = findViewById(R.id.version);
        btn_skipPage = findViewById(R.id.btn_skipPage);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen--;
                    btn_skipPage.setText("跳过 " + recLen + "s");
                    if (recLen < 0) {
                        timer.cancel();
                        btn_skipPage.setVisibility(View.GONE);
                    }
                }
            });
        }
    };

}