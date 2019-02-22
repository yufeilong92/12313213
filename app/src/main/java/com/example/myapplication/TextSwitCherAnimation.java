package com.example.myapplication;


import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication
 * @Package com.example.myapplication
 * @author: L-BackPacker
 * @date: 2019.02.22 上午 9:44
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class TextSwitCherAnimation {

    private AnimationSet InAnimationSet;
    private AnimationSet OutAnimationSet;
    private static final int DURATION = 1000;
    private int marker;
    private TextSwitcher mTvSwc;
    private List<String> mList = new ArrayList<>();
    private int delayTime = 2000;
    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            nextView();
            handler.postDelayed(task, delayTime * 2);
        }
    };

    public TextSwitCherAnimation(TextSwitcher mTvSwc, List<String> mList) {
        this.mTvSwc = mTvSwc;
        this.mList = mList;
    }

    public void start() {
        handler.postDelayed(task, delayTime);
    }

    public void stop() {
        handler.removeCallbacks(task);
    }

    public int getMarker() {
        return marker;
    }

    public TextSwitCherAnimation setText(List<String> mList) {
        this.mList = mList;
        return this;
    }

    public void setDelayTime(int dalayTime) {
        this.delayTime = dalayTime;
    }

    public void create() {
        marker = 0;
        if (mList == null) {
            return;
        }
        if (mTvSwc == null) {
            return;
        }
        mTvSwc.setText(mList.get(0));
        createAnimation();
        mTvSwc.setInAnimation(InAnimationSet);
        mTvSwc.setOutAnimation(OutAnimationSet);
        start();

    }

    private void createAnimation() {
        AlphaAnimation alphaAnimation;
        TranslateAnimation translateAnimation;

        int h = mTvSwc.getHeight();
        if (h <= 0) {
            mTvSwc.measure(0, 0);
            h = mTvSwc.getMeasuredHeight();
        }

        InAnimationSet = new AnimationSet(true);
        OutAnimationSet = new AnimationSet(true);

        alphaAnimation = new AlphaAnimation(0, 1);
        translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, h, Animation.ABSOLUTE, 0);
        InAnimationSet.addAnimation(alphaAnimation);
        InAnimationSet.addAnimation(translateAnimation);
        InAnimationSet.setDuration(DURATION);

        alphaAnimation = new AlphaAnimation(1, 0);
        translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -h);
        OutAnimationSet.addAnimation(alphaAnimation);
        OutAnimationSet.addAnimation(translateAnimation);
        OutAnimationSet.setDuration(DURATION);
    }

    private void nextView() {
        marker = ++marker % mList.size();
        mTvSwc.setText(mList.get(marker));
    }
}
