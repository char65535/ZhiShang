package cn.itcast.zhishang.sqlNotepad.sql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.itcast.zhishang.bean.Notepad;

public class NoteSQLService {
    NoteDBOpenHelper helper;

    public NoteSQLService(Context context) {
        helper = new NoteDBOpenHelper(context);
    }

    public long addInfo(Notepad notes) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", notes.getTitle());
        values.put("content", notes.getContent());
        values.put("time", notes.getTime());
        long rowId = db.insert("notes", null, values);
        db.close();
        return rowId;
    }

    public Notepad find(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("notes", null, "notesId=?", new String[]{id + ""}, null, null, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
                return new Notepad(title, content, time);
            }
        }
        return null;
    }

    public int delete(int notesId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        int rowNumber = db.delete("notes", "notesId=?", new String[]{notesId + ""});
        db.close();
        return rowNumber;
    }

    public int update(Notepad notes) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", notes.getTitle());
        values.put("content", notes.getContent());
        values.put("time", notes.getTime());
        int rowNumber = db.update("notes", values, "notesId=?", new String[]{notes.getId() + ""});
        db.close();
        return rowNumber;
    }

    public List<Notepad> getAllData() {
        SQLiteDatabase db = helper.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.query("notes", null, null, null,
                null, null, "notesId desc");
        List<Notepad> notes = new ArrayList<>();
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("notesId"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
                notes.add(new Notepad(id, title, content, time));
            }
            return notes;
        }
        return null;

//        ArrayList<Notepad> list = new ArrayList<Notepad>();
//        @SuppressLint("Recycle") Cursor cursor = db.query("Notepad", null, null, null, null, null, "name DESC");
//        while (cursor.moveToNext()) {
//            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
//            @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
//            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
//            list.add(new Notepad(title, content, time));
//        }
//        return list;
    }
}













