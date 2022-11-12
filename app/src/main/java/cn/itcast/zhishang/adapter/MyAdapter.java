package cn.itcast.zhishang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.itcast.zhishang.EditActivity;
import cn.itcast.zhishang.OnItemClickListener;
import cn.itcast.zhishang.OnRemoveListener;
import cn.itcast.zhishang.R;
import cn.itcast.zhishang.bean.Notepad;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final List<Notepad> notepads;
    private final Context context;
    private OnItemClickListener listener;
    private OnRemoveListener onRemoveListener;

    public void setOnRemoveListener(OnRemoveListener onRemoveListener) {
        this.onRemoveListener = onRemoveListener;
    }

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
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(notepads.get(position).getTitle());
        holder.content1.setText(notepads.get(position).getContent());
        holder.time.setText(notepads.get(position).getTime());
        holder.pos = position;

    }

    @Override
    public int getItemCount() {
        return notepads == null ? 0 : notepads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, content1, time;
        int pos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content1 = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转修改页面
                    Intent intent_edit = new Intent();
                    intent_edit.setClass(context.getApplicationContext(), EditActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", title.getText().toString().trim());
                    bundle.putString("content", content1.getText().toString().trim());
                    bundle.putString("time", time.getText().toString().trim());
                    bundle.putInt("position", pos);
                    intent_edit.putExtras(bundle);
                    intent_edit.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent_edit);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onRemoveListener != null) {
                        onRemoveListener.onDelete(pos);
                    }
                    return true;
                }
            });
        }
    }

}