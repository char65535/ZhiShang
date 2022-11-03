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

public class RecordActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title, date_now;
    private EditText textArea;
    private ImageView submit;
    private Button skip_notepad;
    private String notepad_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
        getNotepadData();

        title.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));
        submit.setOnClickListener(this);
        skip_notepad.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.submit:
                Toast.makeText(this, "点击了提交按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.skip_notepad:
                Intent skipNotepad_intent = new Intent(this, NotepadActivity.class);
                startActivity(skipNotepad_intent);
                break;
        }
    }
}





