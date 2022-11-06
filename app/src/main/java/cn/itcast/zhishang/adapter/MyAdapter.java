package cn.itcast.zhishang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.itcast.zhishang.R;
import cn.itcast.zhishang.bean.Notepad;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<Notepad> notepads;
    Context context;
    AdapterView.OnItemClickListener listener;

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
        holder.content.setText(notepads.get(position).getContent());
        holder.time.setText(notepads.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return notepads == null ? 0 :  notepads.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, content, time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
        }
    }
}















