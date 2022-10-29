package cn.itcast.zhishang.sql;

import android.content.Context;

public class SQLService {
    DBOpenHelper helper;

    public SQLService(Context context) {
        helper = new DBOpenHelper(context);
    }
}
