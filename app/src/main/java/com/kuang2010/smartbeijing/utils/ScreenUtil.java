package com.kuang2010.smartbeijing.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * author: kuangzeyu2019
 * date: 2021/7/7
 * desc:
 */
public class ScreenUtil {

    public static int[] getScreenWH(Activity context){
        WindowManager windowManager = context.getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return  new int[]{point.x,point.y};
    }
}
