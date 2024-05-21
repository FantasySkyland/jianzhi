package com.example.tijianapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.tijianapplication.R;
import com.example.tijianapplication.utils.DimenUtil;

import java.text.DecimalFormat;

/**
 * Created by zdy On 2024/5/16.
 */
public  class ShiLiEView2 extends androidx.appcompat.widget.AppCompatImageView {
    private int level1With = DimenUtil.getScreenWidth()/2;

    public ShiLiEView2(Context context) {
        super(context);
    }

    public ShiLiEView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShiLiEView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void refreshELevel(int level){
        int with = getEWith(level);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParams.width = with;
        layoutParams.height = with;
        setLayoutParams(layoutParams);
    }
    private int getEWith(int level){
        if (level==1){
            return level1With;
        }else if (level == 2){
            return (int)(level1With*0.75);
        }else if (level == 3){
            return (int)(level1With*0.6);
        }else if (level == 4){
            return (int)(level1With*0.5);
        }else if (level == 5){
            return (int)(level1With*0.375);
        }else if (level == 6){
            return (int)(level1With*0.3);
        }else if (level == 7){
            return (int)(level1With*0.25);
        }else if (level == 8){
            return (int)(level1With*0.1875);
        }else if (level == 9){
            return (int)(level1With*0.15);
        }else if (level == 10){
            return (int)(level1With*0.125);
        }else if (level == 11){
            return (int)(level1With*0.1);
        }else if (level == 12){
            return (int)(level1With*0.075);
        }else if (level == 13){
            return (int)(level1With*0.06);
        }else if (level == 14){
            return (int)(level1With*0.05);
        }else if (level == 15){
            return (int)(level1With*0.0375);
        }else if (level == 16){
            return (int)(level1With*0.03);
        }else if (level == 17){
            return (int)(level1With*0.025);
        }else if (level == 18){
            return (int)(level1With*0.01875);
        }else if (level == 19){
            return (int)(level1With*0.015);
        }else if (level == 20){
            return (int)(level1With*0.0125);
        }else if (level == 21){
            return (int)(level1With*0.01);
        }else if (level == 22){
            return (int)(level1With*0.0075);
        }
        return level1With;
    }
    public static String getShiLiStr(int level){
        double shili = 3.1;
        shili = shili+0.1*level;
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(shili);
    }
}
