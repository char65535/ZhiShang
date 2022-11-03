package cn.itcast.zhishang;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cn.itcast.zhishang.bean.Notepad;
import cn.itcast.zhishang.sqlNotepad.sql.SQLService;

public class NotepadActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView noteName;
    private ImageView add;
    private String title;

    private String textArea;
    private String date_now;

    private RecyclerView mRecyclerView;
    Notepad notes;
    SQLService service;
    ActivityResultLauncher<Intent> launcher;
    private ArrayList<Notepad> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        //获取来自RecordActivity的数据
//        TODO
//        Bundle bundle = getIntent().getExtras();
//        textArea = bundle.getString("textArea");
//        date_now = bundle.getString("date_now");

        initView();
        noteName.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
        add.setOnClickListener(this);
        noteName.setOnClickListener(this);
    }

    private void initView() {
        noteName = findViewById(R.id.note_name);
        mRecyclerView = findViewById(R.id.recyclerview);
        add = findViewById(R.id.add);
        notes = new Notepad();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                MyDialog myDialog = new MyDialog(this, R.style.DialogTheme, new MyDialog.PriorityListener() {
                    @Override
                    public void setActivityTest(String str) {
                        title = str;
                    }
                });
                myDialog.show();
                break;
            case R.id.note_name:
                Toast.makeText(this, textArea + "，" + date_now, Toast.LENGTH_SHORT).show();
                break;
        }
    }

}














