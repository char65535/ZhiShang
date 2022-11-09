package cn.itcast.zhishang;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
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
    private String textArea, date_now;
    private ActivityResultLauncher<Intent> launcher;
    private String title;

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    NoteSQLService service;
    private List<Notepad> notepads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        service = new NoteSQLService(this);
        notepads = service.getAllData();
        //获取来自RecordActivity的数据
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
    }

    //    获取控件
    private void initView() {
        noteName = findViewById(R.id.note_name);
        add = findViewById(R.id.add);
        mRecyclerView =findViewById(R.id.recyclerview);
    }

    //    实现点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
//                为对话框类创建对象
                MyDialog myDialog = new MyDialog(this, R.style.DialogTheme, new PriorixtyListener() {
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

    //创建对话框类
    public class MyDialog extends Dialog {
        private Context context;
        private TextView title_dialog;
        private EditText textArea;
        private ImageView cancel, confirm;
        private PriorixtyListener listener;

        public MyDialog(@NonNull Context context, int theme, PriorixtyListener listener) {
            super(context, theme);
            this.context = context;
            this.listener = listener;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.layout_dialog, null);
            setContentView(view);

            initView();

//            设置对话框位置
            Window window = getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.CENTER;
            window.setAttributes(params);

//            为确定和取消按钮添加点击事件
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.setActivityTest(textArea.getText().toString());
                    dismiss();

                    Intent intent_record = new Intent();
                    intent_record.setClass(context, RecordActivity.class);
                    intent_record.putExtra("title", textArea.getText().toString());
                    startActivity(intent_record);
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "已取消", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            });
        }

        private void initView() {
            title_dialog = findViewById(R.id.title);
            textArea = findViewById(R.id.textArea);
            cancel = findViewById(R.id.cancel);
            confirm = findViewById(R.id.confirm);
        }
    }
}