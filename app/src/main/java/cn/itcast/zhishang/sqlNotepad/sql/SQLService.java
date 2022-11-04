package cn.itcast.zhishang.sqlNotepad.sql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cn.itcast.zhishang.bean.Notepad;

public class SQLService {
    DBOpenHelper helper;

    public SQLService(Context context) {
        helper = new DBOpenHelper(context);
    }

    public long addInfo(Notepad notes) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", notes.getNotepadTitle());
        values.put("content", notes.getNotepadContent());
        values.put("time", notes.getNotepadTime());
        long rowId = db.insert("notes", null, values);
        db.close();
        return rowId;
    }

    public Notepad find(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("notes", null, "notesid=?", new String[]{id + ""}, null, null, null);
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
        values.put("title", notes.getNotepadTitle());
        values.put("content", notes.getNotepadContent());
        values.put("time", notes.getNotepadTime());
        int rowNumber = db.update("notes", values, "notesId=?", new String[]{notes.getId() + ""});
        db.close();
        return rowNumber;
    }

    public ArrayList<Notepad> getAllData() {
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<Notepad> list = new ArrayList<Notepad>();
        @SuppressLint("Recycle") Cursor cursor = db.query("Notepad", null, null, null, null, null, "name DESC");
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
            @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
            list.add(new Notepad(title, content, time));
        }
        return list;
    }
}













