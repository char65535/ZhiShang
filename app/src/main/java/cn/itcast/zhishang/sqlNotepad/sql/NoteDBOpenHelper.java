package cn.itcast.zhishang.sqlNotepad.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDBOpenHelper extends SQLiteOpenHelper {
    public NoteDBOpenHelper(@Nullable Context context) {
        super(context, "notepad.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //不用自增建，因为在外面没法获取到，用时间戳作为每条的id，这样也不会重复
        db.execSQL("CREATE TABLE IF NOT EXISTS notes (notesId varchar(200) ,title varchar(20), content varchar(200), time varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
