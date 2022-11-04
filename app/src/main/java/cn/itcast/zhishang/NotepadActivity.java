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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cn.itcast.zhishang.bean.Notepad;
import cn.itcast.zhishang.sqlNotepad.sql.SQLService;

public class NotepadActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView noteName;
    private ImageView add;
    private String title;

    private Bundle bundle;
    private String textArea, date_now;

    private RecyclerView mRecyclerView;
    Notepad notes;
    SQLService service;
    private ActivityResultLauncher<Intent> launcher;
    private ArrayList<Notepad> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        //获取来自RecordActivity的数据

        initView();
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getData() != null && result.getResultCode() == RESULT_OK) {
                    //此处被隔很久，误区：创建Intent接收数据，实时上应该使用registerForActivityResult的result调用
                    Bundle bundle = result.getData().getExtras();
                    textArea = bundle.getString("textArea");
                    date_now = bundle.getString("date_now");
                }
            }
        });
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
//                为对话框类创建对象
                MyDialog myDialog = new MyDialog(this, R.style.DialogTheme, new PriorixtyListener() {
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

    //创建对话框类
    public class MyDialog extends Dialog {
        private Context context;
        private TextView title;
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

            Window window = getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.CENTER;
            window.setAttributes(params);


            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.setActivityTest(textArea.getText().toString());
                    dismiss();

                    Intent intent_record = new Intent();
                    intent_record.setClass(context, RecordActivity.class);
                    intent_record.putExtra("title", textArea.getText().toString());
//                    context.startActivity(intent_record);
                    launcher.launch(intent_record);
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
            title = findViewById(R.id.title);
            textArea = findViewById(R.id.textArea);
            cancel = findViewById(R.id.cancel);
            confirm = findViewById(R.id.confirm);
        }
    }
}














