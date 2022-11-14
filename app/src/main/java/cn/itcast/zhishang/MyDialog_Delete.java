package cn.itcast.zhishang;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.List;

import cn.itcast.zhishang.adapter.MyAdapter;
import cn.itcast.zhishang.bean.Notepad;
import cn.itcast.zhishang.sqlNotepad.sql.NoteSQLService;

public class MyDialog_Delete extends Dialog {
    private final Context context;
    private final int position;
    private final List<Notepad> notepads;
    private final MyAdapter myAdapter;
    private NoteSQLService service;
    private ImageView cancel, confirm;

    public MyDialog_Delete(Context context, int theme, int position, List<Notepad> notepads, MyAdapter myAdapter, NoteSQLService service) {
        super(context, theme);
        this.context = context;
        this.position = position;
        this.notepads = notepads;
        this.myAdapter = myAdapter;
        this.service = service;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_dialog_delete, null);
        setContentView(view);
//        service = new NoteSQLService(context);

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
                notepads.remove(position);
//                myAdapter.notifyDataSetChanged();
                Log.e("service", service + "");
                Log.e("notepads", notepads + "");
                service.delete(position);
                myAdapter.notifyDataSetChanged();
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initView() {
        cancel = findViewById(R.id.cancel);
        confirm = findViewById(R.id.confirm);
    }
}