package cn.itcast.zhishang.sql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.itcast.zhishang.bean.Person;

public class SQLService {
    DBOpenHelper helper;

    public SQLService(Context context) {
        helper = new DBOpenHelper(context);
    }

    public long addInfo(Person person) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", person.getName());
        values.put("email", person.getEmail());
        values.put("pwd", person.getPwd());
        values.put("rePwd", person.getRePwd());
        long rowId = db.insert("person", null, values);
        db.close();
        return rowId;
    }

    public Person find(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("person", null, "personid=?", new String[]{id + ""}, null, null, null);
        if (cursor.getCount() != 0) {
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                @SuppressLint("Range") String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
                @SuppressLint("Range") String rePwd = cursor.getString(cursor.getColumnIndex("rePwd"));
                return new Person(name, email, pwd, rePwd);
            }
        }
        return null;
    }

    public int delete(int personId) {
        SQLiteDatabase db = helper.getReadableDatabase();
        int rowNumber = db.delete("person", "personId=?", new String[]{personId + ""});
        db.close();
        return rowNumber;
    }

    public int update(Person person) {
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", person.getName());
        values.put("email", person.getEmail());
        values.put("pwd", person.getPwd());
        values.put("rePwd", person.getRePwd());
        int rowNumber = db.update("person", values, "personId=?", new String[]{person.getId() + ""});
        db.close();
        return rowNumber;
    }
}













