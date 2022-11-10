package cn.itcast.zhishang;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.itcast.zhishang.bean.Notepad;
import cn.itcast.zhishang.sqlNotepad.sql.NoteSQLService;

public class NotepadActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView noteName;
    private ImageView add;

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;
    private List<Notepad> notepads;
    private NoteSQLService service;

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

    //创建对话框类
    public class MyDialog extends Dialog {
        private final Context context;
        private EditText textArea;
        private ImageView cancel, confirm;
        private final PriorixtyListener listener;

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
            textArea = findViewById(R.id.textArea);
            cancel = findViewById(R.id.cancel);
            confirm = findViewById(R.id.confirm);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private final List<Notepad> notepads;
        private final Context context;
        private OnItemClickListener listener;

        public MyAdapter(List<Notepad> notepads, Context context) {
            this.notepads = notepads;
            this.context = context;
        }

        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
            holder.title.setText(notepads.get(position).getTitle());
            holder.content1.setText(notepads.get(position).getContent());
            holder.time.setText(notepads.get(position).getTime());
        }

        @Override
        public int getItemCount() {
            return notepads == null ? 0 : notepads.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView title, content1, time;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
                content1 = itemView.findViewById(R.id.content);
                time = itemView.findViewById(R.id.time);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent_adapter = new Intent(NotepadActivity.this, RecordActivity.class);
                        intent_adapter.putExtra("title", title.getText().toString().trim());
                        intent_adapter.putExtra("content", content1.getText().toString().trim());
                        startActivity(intent_adapter);
                    }
                });
            }
        }

    }
}













