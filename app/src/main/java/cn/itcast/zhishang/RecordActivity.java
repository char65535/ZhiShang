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

import java.util.Calendar;

import cn.itcast.zhishang.bean.Notepad;
import cn.itcast.zhishang.sqlNotepad.sql.NoteSQLService;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title, date_now;
    private EditText textArea;
    private ImageView submit;
    private Button skip_notepad;
    private String notepad_title, date, textAreaData;
    private int mYear, mMath, mDay, mWay;
    NoteSQLService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
//        获取由Notepad传来的数据
        getNotepadData();
//        为title设置字体
        title.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));//更换title字体

        calenderData();//获取年月日
        date = mWay + " " + mYear + "." + mMath + "." + mDay;
//        将日期添加到date_now控件上
        date_now.setText(date);
//        为提交,返回按钮添加事件
        submit.setOnClickListener(this);
        skip_notepad.setOnClickListener(this);
//        创建NoteSQLService实例化对象
        service = new NoteSQLService(getApplicationContext());
    }

    private void calenderData() {
        Calendar instance = Calendar.getInstance();
        mYear = instance.get(Calendar.YEAR);
        mMath = instance.get(Calendar.MARCH) + 1;
        mDay = instance.get(Calendar.DAY_OF_MONTH);
        mWay = instance.get(Calendar.DAY_OF_WEEK) - 1;
    }

    private void getNotepadData() {
        Intent intent_notepad = getIntent();
        notepad_title = intent_notepad.getStringExtra("title");
        title.setText(notepad_title);
    }

    private void initView() {
        title = findViewById(R.id.title);
        date_now = findViewById(R.id.date_now);
        textArea = findViewById(R.id.textArea);
        submit = findViewById(R.id.submit);
        skip_notepad = findViewById(R.id.skip_notepad);
    }

    @Override
    public void onClick(View v) {
        Notepad notes = null;

        switch (v.getId()) {
            case R.id.submit:
                textAreaData = textArea.getText().toString();
//                添加到数据库
                notes = new Notepad(notepad_title, textAreaData, date);
                long rowId = service.addInfo(notes);
                if (rowId == -1) {
                    Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                }

                Intent submit_intent = new Intent();
                submit_intent.setClass(this, NotepadActivity.class);
                startActivity(submit_intent);
                finish();
                break;

            case R.id.skip_notepad:
//                跳转NotepadActivity页面
                Intent skipNotepad_intent = new Intent(this, NotepadActivity.class);
                startActivity(skipNotepad_intent);
                finish();
                break;
        }
    }
}




