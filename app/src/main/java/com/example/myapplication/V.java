package com.example.myapplication;

import android.app.Activity;
import android.view.View;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyApplication
 * @Package com.example.myapplication
 * @author: L-BackPacker
 * @date: 2019.02.21 下午 4:00
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
public class V {
    public static <T extends View> T f(Activity activity, int id) {
        return (T) activity.findViewById(id);
    }

    public static <T extends View> T f(View view, int id) {
        return (T) view.findViewById(id);
    }
}
