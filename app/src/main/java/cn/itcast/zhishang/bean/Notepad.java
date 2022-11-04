package cn.itcast.zhishang.bean;

public class Notepad extends Person {
    private Integer id;                  //记录的id
    private String notepadTitle;
    private String notepadContent;   //记录的内容
    private String notepadTime;       //保存记录的时间

    public Notepad(String notepadTitle, String notepadContent, String notepadTime) {
        this.notepadTitle = notepadTitle;
        this.notepadContent = notepadContent;
        this.notepadTime = notepadTime;
    }

    public Notepad(Integer id, String notepadTitle, String notepadContent, String notepadTime) {
        this.id = id;
        this.notepadTitle = notepadTitle;
        this.notepadContent = notepadContent;
        this.notepadTime = notepadTime;
    }

    public Notepad() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotepadTitle() {
        return notepadTitle;
    }

    public void setNotepadTitle(String notepadTitle) {
        this.notepadTitle = notepadTitle;
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
                ", notepadTitle='" + notepadTitle + '\'' +
                ", notepadContent='" + notepadContent + '\'' +
                ", notepadTime='" + notepadTime + '\'' +
                '}';
    }
}
