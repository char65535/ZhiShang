package cn.itcast.zhishang;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.itcast.zhishang.bean.Notepad;
import cn.itcast.zhishang.bean.RefreshNotepadEvent;
import cn.itcast.zhishang.sqlNotepad.sql.NoteSQLService;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView time;
    private EditText title, content;
    private ImageView submit;
    private Button back;

    private String str_title, str_content, str_time;
    private String now_title, now_content, now_time, now_id;
    private int position;

    private NoteSQLService service;
    private List<Notepad> notepads;
    private Notepad notepad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
//        获取控件
        initView();
//        设置字体
        title.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
        content.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
//        接收来自NotepadActivity页面的数据
        receive_NotepadsActivity();
//        初始化控件
        set_control();
//        创建NoteSQLService实例对象
        service = new NoteSQLService(this);
        notepads = service.getAllData();
        AllClickListener();
    }


    private void initView() {
        title = findViewById(R.id.title);
        content = findViewById(R.id.textArea);
        time = findViewById(R.id.date_now);
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);
    }

    private void receive_NotepadsActivity() {
        Bundle bundle = getIntent().getExtras();
        str_title = bundle.getString("title");
        str_content = bundle.getString("content");
        str_time = bundle.getString("time");
        position = bundle.getInt("position");
        now_id = bundle.getString("now_id");
    }

    private void set_control() {
        title.setText(str_title);
        content.setText(str_content);
        time.setText(str_time);
    }

    private void AllClickListener() {
        submit.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
//                删除该行数据，并创建新数据
                now_title = title.getText().toString();
                now_content = content.getText().toString();
                now_time = time.getText().toString();
                notepad = new Notepad(now_id, now_title, now_content, now_time);
                long rowId = service.addInfo(notepad);
                if (rowId == -1) {
                    Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                }
                //修改完发通知刷新NotepadActivity的列表
                EventBus.getDefault().post(new RefreshNotepadEvent());
                finish();
                break;
            case R.id.back:
                Intent skipNotepad_intent = new Intent(this, NotepadActivity.class);
                startActivity(skipNotepad_intent);
                finish();
                break;
        }
    }
}






