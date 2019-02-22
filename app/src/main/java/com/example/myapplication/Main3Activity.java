package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStart;
    private TextView mTvStartTime;
    private TextView mTvEntTime;
    private Context mContext;
    private DbHelpManger helpManger;
    private TextView mTvC;
    private Button mBtnDelete;
    private SQLiteDatabase database;
    private Button mBtnQuery;
    private Button mBtnDelete1;
    private Button mBtnQueryall;
    private TextView mTvItem;
    private Button mBtnDelete2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        initData();
    }

    private void initData() {
        helpManger = DbHelpManger.get_Instance(mContext);
        database = helpManger.getWritableDatabase();
    }

    private void initView() {
        mContext = this;
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mTvStartTime = (TextView) findViewById(R.id.tv_start_time);
        mTvEntTime = (TextView) findViewById(R.id.tv_ent_time);

        mBtnStart.setOnClickListener(this);
        mTvC = (TextView) findViewById(R.id.tv_c);
        mTvC.setOnClickListener(this);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnDelete.setOnClickListener(this);
        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mBtnQuery.setOnClickListener(this);
        mBtnDelete1 = (Button) findViewById(R.id.btn_delete1);
        mBtnDelete1.setOnClickListener(this);
        mBtnQueryall = (Button) findViewById(R.id.btn_queryall);
        mBtnQueryall.setOnClickListener(this);
        mTvItem = (TextView) findViewById(R.id.tv_item);
        mTvItem.setOnClickListener(this);
        mBtnDelete2 = (Button) findViewById(R.id.btn_delete2);
        mBtnDelete2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                long l = System.currentTimeMillis();
                mTvStartTime.setText(String.valueOf(l));
                database.beginTransaction();
                for (int i = 0; i < 100; i++) {
                    ContentValues values = new ContentValues();
                    values.put("name", "小明"+i);
                    values.put("age", i);
                    values.put("id2", i);
                    values.put("sex", i);
                    values.put("phone", "18317837561");
                    database.insert(DbHelpManger.TANLENAME, null, values);
                }
                for (int i = 0; i < 100; i++) {
                    ContentValues values = new ContentValues();
                    values.put("name", "小明"+i);
                    values.put("age", i);
                    values.put("id2", i);
                    values.put("sex", i);
                    values.put("phone", "18317837561");
                    database.insert(DbHelpManger.TANLENAME, null, values);
                }
                database.setTransactionSuccessful();
                database.endTransaction();
                long l1 = System.currentTimeMillis();
                mTvEntTime.setText(String.valueOf(l1));
                mTvC.setText(String.valueOf(l1 - l));
                break;
            case R.id.btn_delete:
                database.delete(DbHelpManger.TANLENAME,
                        " id2 in =? ",
                        new String[]{"(1,2)"});
//                String sql="delete from "+DbHelpManger.TANLENAME;
                break;
            case R.id.btn_query:
                String sql = "select count (*) from " + DbHelpManger.TANLENAME;
                SQLiteStatement statement = database.compileStatement(sql);
                long l2 = statement.simpleQueryForLong();
                mTvC.setText(String.valueOf(l2));
                break;
            case R.id.btn_delete1:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(1);
                stringBuffer.append(",");
                stringBuffer.append(2);
                database.delete(DbHelpManger.TANLENAME,
                        "id2 in (" + stringBuffer.toString() + ")", null);
                break;

            case R.id.btn_queryall:
                StringBuffer buffer = new StringBuffer();
                long l3 = System.currentTimeMillis();
//                for (int i = 0; i < 500; i++) {
//                    Cursor query = database.query(DbHelpManger.TANLENAME, null, "id2=?",
//                            new String[]{String.valueOf(i)}, null, null,
//                            null);
//                    while (query.moveToNext()) {
//                        String string = getString(query, "name");
//                        buffer.append(string + "/");
//                    }
//                }
                Cursor query = database.query(DbHelpManger.TANLENAME, null, "id2 in(1,2,3,4,5) and sex in(1,1,1,1,1)", null, null, null, null);
                while (query.moveToNext()){
                    String string = getString(query, "name");
                    buffer.append(string + "/");
                }
                long l4 = System.currentTimeMillis();
                Log.e("===", "onClick: " + (l4 - l3));
                mTvItem.setText(buffer.toString());
                break;
            case R.id.btn_delete2:
                database.delete(DbHelpManger.TANLENAME, null, null);
                break;
        }
    }

    private String getString(Cursor cursor, String key) {
        int index = cursor.getColumnIndex(key);
        return cursor.getString(index);

    }
}
