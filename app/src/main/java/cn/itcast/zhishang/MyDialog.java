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
import android.widget.Toast;

import androidx.annotation.NonNull;

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
                intent_record.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        textArea = findViewById(R.id.textArea);
        cancel = findViewById(R.id.cancel);
        confirm = findViewById(R.id.confirm);
    }
}