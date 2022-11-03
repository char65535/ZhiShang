package cn.itcast.zhishang.bean;

public class Notepad extends Person {
    private Integer id;                  //记录的id
    private String notepadContent;   //记录的内容
    private String notepadTime;       //保存记录的时间

    public Notepad(Integer id, String notepadContent, String notepadTime) {
        this.id = id;
        this.notepadContent = notepadContent;
        this.notepadTime = notepadTime;
    }

    public Notepad(String notepadContent, String notepadTime) {
        this.id = id;
        this.notepadContent = notepadContent;
        this.notepadTime = notepadTime;
    }

    public Notepad() {
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

    @Override
    public String toString() {
        return "Notepad{" +
                "id=" + id +
                ", notepadContent='" + notepadContent + '\'' +
                ", notepadTime='" + notepadTime + '\'' +
                '}';
    }

}
