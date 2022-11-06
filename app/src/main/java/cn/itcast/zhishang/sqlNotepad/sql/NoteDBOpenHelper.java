package cn.itcast.zhishang.sqlNotepad.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDBOpenHelper extends SQLiteOpenHelper {
    public NoteDBOpenHelper(@Nullable Context context) {
        super(context, "notepad.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS notes (notesId integer " +
                "primary key autoincrement,title varchar(20), content varchar(200), time varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
