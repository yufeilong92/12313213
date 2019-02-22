package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication
 * @Package com.example.myapplication
 * @author: L-BackPacker
 * @date: 2019.02.13 下午 6:37
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class DbHelpManger extends SQLiteOpenHelper {
    public static final String TANLENAME = "user";
    public static final String TANLENAME2 = "user2";
    public static final String DATABASENAME = "data.db";

    private static volatile DbHelpManger _singleton;
    private Context mContext;
    public static DbHelpManger get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (DbHelpManger.class) {
                if (_singleton == null) {
                    _singleton = new DbHelpManger(context);
                }
            }
        }
        return _singleton;
    }

    public DbHelpManger(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TANLENAME + " (" +
                "id integer primary key autoincrement," +
                "id2 integer," +
                "name text," +
                "age integer," +
                "sex integer," +
                "phone text);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
