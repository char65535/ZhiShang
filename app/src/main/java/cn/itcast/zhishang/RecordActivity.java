package cn.itcast.zhishang;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title, date_now;
    private EditText textArea;
    private ImageView submit;
    private Button skip_notepad;
    private String notepad_title, date, textAreaData;

    int mYear, mMath, mDay, mWay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
        getNotepadData();//获取由Notepad传来的数据
        title.setTypeface(Typeface.createFromAsset(this.getAssets(), "fonts/huakangw5.ttc"));//更换title字体

        calenderData();//获取年月日
        date = mWay + " " + mYear + "." + mMath + "." + mDay;
        date_now.setText(date);

        submit.setOnClickListener(this);
        skip_notepad.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.submit:
                textAreaData = textArea.getText().toString();
                Intent submit_intent = new Intent();
                submit_intent.setClass(this, NotepadActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("textArea", textAreaData);
                bundle.putString("date_now", date);
                submit_intent.putExtras(bundle);
                setResult(RESULT_OK, submit_intent);
                finish();
                break;

            case R.id.skip_notepad:
                Intent skipNotepad_intent = new Intent(this, NotepadActivity.class);
                startActivity(skipNotepad_intent);
                finish();

                break;
        }
    }
}




