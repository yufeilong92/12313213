package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvContent;
    private Button mBtn;
    int number = 15;
    private ImageView mIvHear;
    private TextView mTvWith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initView();
        initData();

    }

    String ab = "\t";
    String d = "\t";

    @Override
    protected void onStart() {
        super.onStart();
//        initHear();
    }

    int cound = 0;

    private void initHear() {
        final String content = "测hi是";
        final int width = mIvHear.getLayoutParams().width;
  /*      int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mTvWith.measure(spec, spec);*/
        mTvWith.setText(d);
        ViewTreeObserver treeObserver = mTvWith.getViewTreeObserver();
        treeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                int wd = mTvWith.getMeasuredWidth();
                if (wd == 0) return false;
                cound = wd;
                String getwith = getwith(wd, width);
                String concat = getwith.concat(content);
                mTvContent.setText(concat);
                cound = 0;
                ab = "\t";
                mTvWith.setVisibility(View.GONE);
                return true;
            }
        });


    }

    private String getwith(int wd, int width) {
        if (cound < width) {
            ab = ab.concat(d);
            cound += wd;
            getwith(wd, width);
        }
        return ab;
    }


    private void initData() {
       String string="";
        String a = string.concat("测hi是");
        mTvContent.setText(a);
    }

    private void initView() {
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        mIvHear = (ImageView) findViewById(R.id.iv_hear);
        mIvHear.setOnClickListener(this);
        mTvWith = (TextView) findViewById(R.id.tv_with);
        mTvWith.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                ++number;
                mTvContent.setTextSize(number);
                break;
        }
    }
}
