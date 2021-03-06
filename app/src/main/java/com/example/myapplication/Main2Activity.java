package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnContent;
    private Button mBtnQuery;
    private TextView mTvContent;
    private Context mContext;
    private DbHelp mHelp;
    private TextView mTvContent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();

    }

    private void initData() {
        mHelp = DbHelp.get_Instance(mContext);
    }

    private void initView() {
        mContext = this;
        mBtnContent = (Button) findViewById(R.id.btn_content);
        mBtnQuery = (Button) findViewById(R.id.btn_query);
        mTvContent = (TextView) findViewById(R.id.tv_content);

        mBtnContent.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
        mTvContent1 = (TextView) findViewById(R.id.tv_content1);
        mTvContent1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_content:
                SQLiteDatabase database = mHelp.getWritableDatabase(DbHelp.PASSWORD);
                ContentValues values = new ContentValues();
                values.put("name", "小明");
                values.put("age", 2);
                values.put("sex", 1);
                values.put("phone", "18317837561");
                database.insert(DbHelp.DBTABLENAMEONE, null, values);
                break;
            case R.id.btn_query:
                SQLiteDatabase database1 = mHelp.getReadableDatabase(DbHelp.PASSWORD);
                Cursor query = database1.query(DbHelp.DBTABLENAMEONE, null, null, null, null,
                        null, null);
                StringBuffer buffer = new StringBuffer();
                while (query.moveToNext()) {
                    String name = getString(query, "name");
                    int age = getInteger(query, "age");
                    int sex = getInteger(query, "sex");
                    String phone = getString(query, "phone");
                    buffer.append(name);
                    buffer.append(age);
                    buffer.append(sex);
                    buffer.append(phone);
                }
                query.close();
                Cursor query1 = database1.query(DbHelp.DBTABLENAMEONE, null, null, null, null,
                        null, null);
                StringBuffer buffer1 = new StringBuffer();
                while (query1.moveToNext()) {
                    String name = getString(query1, "name");
                    int age = getInteger(query1, "age");
                    int sex = getInteger(query1, "sex");
                    String phone = getString(query1, "phone");
                    buffer1.append(name);
                    buffer1.append(age);
                    buffer1.append(sex);
                    buffer1.append(phone);
                }
                mTvContent.setText(buffer.toString());
                mTvContent1.setText(buffer1.toString());
                break;
        }
    }

    private int getInteger(Cursor cursor, String key) {
        int columnIndex = cursor.getColumnIndex(key);
        return cursor.getInt(columnIndex);
    }

    private String getString(Cursor cursor, String key) {
        int columnIndex = cursor.getColumnIndex(key);
        return cursor.getString(columnIndex);
    }

    private double getDouble(Cursor cursor, String key) {
        int columnIndex = cursor.getColumnIndex(key);
        return cursor.getDouble(columnIndex);
    }

    private byte[] getByte(Cursor cursor, String key) {
        int columnIndex = cursor.getColumnIndex(key);
        return cursor.getBlob(columnIndex);
    }

    private float getfloat(Cursor cursor, String key) {
        int columnIndex = cursor.getColumnIndex(key);
        return cursor.getFloat(columnIndex);
    }

}
