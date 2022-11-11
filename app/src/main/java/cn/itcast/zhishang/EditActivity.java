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

import java.util.List;

import cn.itcast.zhishang.bean.Notepad;
import cn.itcast.zhishang.sqlNotepad.sql.NoteSQLService;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView time;
    private EditText title, content;
    private ImageView submit;
    private Button back;

    private String str_title, str_content, str_time;
    private int position;

    private NoteSQLService service;
    private List<Notepad> notepads;
    private Notepad notepad;
    private NotepadActivity.MyAdapter myAdapter;

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
//        为控件设置数据
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
                notepad = new Notepad(str_title, str_content, str_time);
                Toast.makeText(this, notepad + "", Toast.LENGTH_SHORT).show();
                itemNotifyItemRemoved();
                itemNotifyItemInsert();
                break;
            case R.id.back:
                Intent skipNotepad_intent = new Intent(this, NotepadActivity.class);
                startActivity(skipNotepad_intent);
                finish();
                break;
        }
    }

    private void itemNotifyItemRemoved() {
        notepads.remove(position);
        myAdapter.notifyItemRemoved(position);
    }

    private void itemNotifyItemInsert() {
        notepads.add(position, notepad);
        myAdapter.notifyItemInserted(position);
    }


}






