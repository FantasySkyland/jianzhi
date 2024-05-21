package com.example.tijianapplication.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.parttime.base.base.App;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/23 09:36
 * @describe ：
 */
public class DimenUtil {

    // dip转像素
    public static int dipToPixels(Context context , final float dip) {
        final float SCALE = context.getResources().getDisplayMetrics().density;
        return (int) (dip * SCALE + 0.5f);
    }
    // dip转像素
    public static int dipToPixels( final float dip) {
        final float SCALE = App.getInstant().getResources().getDisplayMetrics().density;
        return (int) (dip * SCALE + 0.5f);
    }
    // 像素转dip
    public static float pixelsToDip(Context context, final int pixels) {
        final float SCALE = context.getResources().getDisplayMetrics().density;
        return pixels / SCALE;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth() {
        Display display =
                ((WindowManager) App.getInstant().getSystemService(Context.WINDOW_SERVICE))
                        .getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm.widthPixels;
    }


    public static int getScreenHeight() {
        Display display =
                ((WindowManager) App.getInstant().getSystemService(Context.WINDOW_SERVICE))
                        .getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm.heightPixels;
    }
}
