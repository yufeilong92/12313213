package com.example.myapplication;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication
 * @Package com.example.myapplication
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2019.02.12 下午 5:37
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class DbHelp extends SQLiteOpenHelper {
    public static final String DBTABLENAME = "userinfom.db";
    public static final String DBTABLENAMEONE = "myuser";

    public static final String PASSWORD = "xuechuan852";
    private static volatile DbHelp _singleton;

    private Context mContext;

    public static DbHelp get_Instance(Context context) {
        if (_singleton == null) {
            synchronized (DbHelp.class) {
                if (_singleton == null) {
                    _singleton = new DbHelp(context);
                }
            }
        }
        return _singleton;
    }

    public DbHelp(Context context) {
        super(context, DBTABLENAME, null, 1);
        SQLiteDatabase.loadLibs(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + DBTABLENAMEONE + " (" +
                "id integer primary key autoincrement," +
                "name text," +
                "age integer," +
                "sex integer," +
                "phone text);";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
