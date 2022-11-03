package cn.itcast.zhishang;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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

import androidx.annotation.NonNull;

public class MyDialog extends Dialog {
    private Context context;
    private TextView title;
    private EditText textArea;
    private ImageView cancel, confirm;

    public interface PriorityListener {
        void setActivityTest(String str);
    }

    private PriorityListener listener;

    public MyDialog(@NonNull Context context, int theme, PriorityListener listener) {
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
                Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                listener.setActivityTest(textArea.getText().toString());
                dismiss();

                Intent intent_record = new Intent();
                intent_record.setClass(context, RecordActivity.class);
                intent_record.putExtra("title", textArea.getText().toString());
                context.startActivity(intent_record);
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












