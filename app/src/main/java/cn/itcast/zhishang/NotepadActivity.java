package cn.itcast.zhishang;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

    private RecyclerView mRecyclerView;
    Notepad notes;
    SQLService service;
    ActivityResultLauncher<Intent> launcher;
    private ArrayList<Notepad> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        initView();
        noteName.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
        add.setOnClickListener(this);

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

            //                Intent intent_record = new Intent();
//                intent_record.setClass(this, RecordActivity.class);
//                startActivity(intent_record);
        }
    }


}














