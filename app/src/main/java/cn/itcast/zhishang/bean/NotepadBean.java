package cn.itcast.zhishang.bean;

import java.util.ArrayList;

public class NotepadBean extends Person {
    private Integer id;                  //记录的id
    private String notepadContent;   //记录的内容
    private String notepadTime;       //保存记录的时间

    public NotepadBean(Integer id, String notepadContent, String notepadTime) {
        this.id = id;
        this.notepadContent = notepadContent;
        this.notepadTime = notepadTime;
    }

    public NotepadBean(String notepadContent, String notepadTime) {
        this.id = id;
        this.notepadContent = notepadContent;
        this.notepadTime = notepadTime;
    }

    @Override
    public String toString() {
        return "NotepadBean{" +
                "id=" + id +
                ", notepadContent='" + notepadContent + '\'' +
                ", notepadTime='" + notepadTime + '\'' +
                '}';
    }

    public NotepadBean() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotepadContent() {
        return notepadContent;
    }

    public void setNotepadContent(String notepadContent) {
        this.notepadContent = notepadContent;
    }

    public String getNotepadTime() {
        return notepadTime;
    }

    public void setNotepadTime(String notepadTime) {
        this.notepadTime = notepadTime;
    }

}
