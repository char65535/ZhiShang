package cn.itcast.zhishang;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    MyDialog myDialog;
    ImageView bt_confirm, bt_cancel;
    String title;

    private TextView title_dialog;
    private EditText textArea_dialog;
    private ImageView cancel_dialog, confirm_dialog;

    TextView tv2;

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


        tv2 = findViewById(R.id.textView2);
        tv2.setText(title);

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
                useDialog();
//                Intent intent_record = new Intent();
//                intent_record.setClass(this, RecordActivity.class);
//                startActivity(intent_record);
        }
    }

    private void useDialog() {
        View view = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        myDialog = new MyDialog(this, 0, 0, view, R.style.DialogTheme);
        myDialog.setCancelable(true);
        initViewDialog(view);

        title_dialog.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
        title = textArea_dialog.getText().toString().trim();
        confirm_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                Toast.makeText(NotepadActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
            }
        });
        cancel_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                Toast.makeText(NotepadActivity.this, "已取消", Toast.LENGTH_SHORT).show();
            }
        });

        myDialog.show();
    }

    private void initViewDialog(View view) {
        title_dialog = view.findViewById(R.id.title);
        textArea_dialog = view.findViewById(R.id.textArea);
        cancel_dialog = view.findViewById(R.id.cancel);
        confirm_dialog = view.findViewById(R.id.confirm);
    }
}














