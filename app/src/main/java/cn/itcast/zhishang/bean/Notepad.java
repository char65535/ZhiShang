package cn.itcast.zhishang.bean;

import android.os.SystemClock;
import android.util.Log;

import java.io.Serializable;

public class Notepad implements Serializable {
    private String id;                  //记录的id ,用时间戳这样是唯一值
    private String title;
    private String content;   //记录的内容
    private String time;       //保存记录的时间

    public Notepad(String id, String title, String content, String time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public Notepad(String title, String content, String time) {
        this.id = System.currentTimeMillis()+"";
        Log.i("onClick100",id);
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public Notepad() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Notepad{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
