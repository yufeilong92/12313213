package com.example.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        initView();
    }

    private void initView() {
        Button btn = V.f(this, R.id.btn_content);
        Button btn1 = V.f(this, R.id.btn_contentOne);
        TextSwitcher tvswt = V.f(this, R.id.tv_sw);
        setOnclickListener(btn);
        setOnclickListener(btn1);
        tvswt.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new TextView(Main5Activity.this);
            }
        });
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("循环说据" + i);
        }
        TextSwitCherAnimation switCherAnimation = new TextSwitCherAnimation(tvswt, list);
        switCherAnimation.setDelayTime(500);
        switCherAnimation.create();
    }


    public void setOnclickListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main5Activity.this, "按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
