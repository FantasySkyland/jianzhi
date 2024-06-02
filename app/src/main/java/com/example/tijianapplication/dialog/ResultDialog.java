package com.example.tijianapplication.dialog;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.tijianapplication.R;
import com.parttime.base.dialog.BaseDialog;

import butterknife.BindView;

/**
 * Created by zdy On 2020/1/6.
 */
public class ResultDialog extends BaseDialog {
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;
    @BindView(R.id.tv_content)
    TextView tv_content;
    private ClickListener clickListener;

    public ClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void  onClick();
    }
    public ResultDialog(Context context) {
        super(context);
    }

    public void setResult(double result){
        String sta = "";
        if (result<18.5){
            sta = " too light";
        }else if (result<24){
            sta = " moderate";
        }else if (result<28){
            sta = " heavy";
        }else if (result<34){
            sta = " obesity";
        }else if (result<34){
            sta = " too obesity";
        }
        tv_content.setText(String.format("Your BMI is %.2f \n\nYour weight status is %s" ,result,sta));
    }
    public void setSmokeResult(int count,String result){
        tv_content.setText(String.format("You smoked about %d cigarettes in total\nThe life span shortened by this is %s days",count,result));
    }
    public void setCalculatorResult(int nl,int sg,int tz,int male,int level){
        double bmr ;
        if(male==1){
            bmr = 10 * tz + 6.25 * sg - 5 * nl +5;
        }else {
            bmr = 10 * tz + 6.25 * sg - 5 * nl -161;
        }

        if (level == 1){
            bmr = bmr*1.2;
        }else if (level==2){
            bmr = bmr*1.375;
        }else if (level==3){
            bmr = bmr*1.55;
        }else if (level==4){
            bmr = bmr*1.725;
        }else if (level==5){
            bmr = bmr*1.9;
        }else if (level==6){
            bmr = bmr*2.4;
        }
        tv_content.setText("Your daily calorie intake is about :"+bmr);
    }
    public void setRateResult(int nl,int xl){
        int maxRate = 220-nl;
        int targetRate = (int)(0.8*maxRate);
        String fw ;
        if(nl<30){
            fw = "140~170";
        }else if ( nl<40){
            fw = "133~162";
        }else if ( nl<50){
            fw = "126~153";
        }else if ( nl<60){
            fw = "119~145";
        }else if ( nl<70){
            fw = "126~153";
        }else if ( nl<80){
            fw = "105~129";
        }else {
            fw = "98~119";
        }
        tv_content.setText(String.format("Your target heart rate ideal value is: %d\nYour target heart rate range is: %s\nYour maximum heart rate is %d",
                targetRate,fw,maxRate));
        tv_confirm.setBackgroundResource(R.drawable.shape_cn22_eb4848);
    }
    @Override
    protected int setContentLayout() {
        return R.layout.dialog_confirm;
    }

    @Override
    protected void initView(View root) {
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (clickListener!=null){
                   clickListener.onClick();
               }
            }
        });
    }
}
