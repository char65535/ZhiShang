package cn.itcast.zhishang.sqlNotepad.sql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cn.itcast.zhishang.bean.NotepadBean;

public class SQLService {
    DBOpenHelper helper;

    public SQLService(Context context) {
        helper = new DBOpenHelper(context);
    }

    public long addInfo(NotepadBean notes) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", notes.getName());
        values.put("email", notes.getEmail());
        long rowId = db.insert("notes", null, values);
        db.close();
        return rowId;
    }

    public NotepadBean find(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("notes", null, "notesid=?", new String[]{id + ""}, null, null, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                return new NotepadBean(name, email);
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

    public int update(NotepadBean notes) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("notepadContent", notes.getNotepadContent());
        values.put("notepadTime", notes.getNotepadTime());
        int rowNumber = db.update("notes", values, "notesId=?", new String[]{notes.getId() + ""});
        db.close();
        return rowNumber;
    }

    public ArrayList<NotepadBean> getAllData() {
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<NotepadBean> list = new ArrayList<NotepadBean>();
        @SuppressLint("Recycle") Cursor cursor = db.query("NotepadBean", null, null, null, null, null, "name DESC");
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String notepadContent = cursor.getString(cursor.getColumnIndex("notepadContent"));
            @SuppressLint("Range") String notepadTime = cursor.getString(cursor.getColumnIndex("notepadTime"));
            list.add(new NotepadBean(notepadContent, notepadTime));
        }
        return list;
    }
}













