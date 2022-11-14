package cn.itcast.zhishang;

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.itcast.zhishang.adapter.MyAdapter;
import cn.itcast.zhishang.bean.Notepad;
import cn.itcast.zhishang.sqlNotepad.sql.NoteSQLService;

public class NotepadActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView noteName;
    private ImageView add;

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private List<Notepad> notepads;
    private NoteSQLService service;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

//        遍历数据库
        service = new NoteSQLService(this);
        notepads = service.getAllData();

        initView();
//        为noteName设置字体
        noteName.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));

//        添加点击事件
        add.setOnClickListener(this);
        noteName.setOnClickListener(this);

//        添加列表
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myAdapter = new MyAdapter(notepads, getApplicationContext());
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnRemoveListener(new OnRemoveListener() {
            @Override
            public void onDelete(int i) {
                MyDialog_Delete myDialog_delete = new MyDialog_Delete(NotepadActivity.this, R.style.DialogTheme, i, notepads, myAdapter, service);
                myDialog_delete.show();
            }
        });
    }

    //    获取控件
    private void initView() {
        noteName = findViewById(R.id.note_name);
        add = findViewById(R.id.add);
        mRecyclerView = findViewById(R.id.recyclerview);
    }

    //    实现点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
//                为对话框类创建对象
                MyDialog myDialog = new MyDialog(this, R.style.DialogTheme, new PriorixtyListener() {
                    private String title;
                    @Override
                    public void setActivityTest(String str) {
                        title = str;
                    }
                });
//                显示对话框
                myDialog.show();
                break;
            case R.id.note_name:
//                显示notepad，判断是否添加到数据库
                Toast.makeText(this, "" + notepads, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}













