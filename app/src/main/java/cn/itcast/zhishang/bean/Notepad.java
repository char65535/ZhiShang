package cn.itcast.zhishang.bean;

import java.io.Serializable;

public class Notepad implements Serializable {
    private Integer id;                  //记录的id
    private String title;
    private String content;   //记录的内容
    private String time;       //保存记录的时间

    public Notepad(Integer id, String title, String content, String time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public Notepad(String title, String content, String time) {
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public Notepad() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
