package com.example.tijianapplication.activity;


import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tijianapplication.R;
import com.example.tijianapplication.fragment.SlbFragment;
import com.example.tijianapplication.fragment.TestEyesFragment;
import com.example.tijianapplication.utils.StatusBarUtils;
import com.example.tijianapplication.view.ShiLiEView2;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.parttime.base.base.BaseActivity;
import com.parttime.base.constants.RoutMap;
import com.parttime.base.rx.RxEvent;

import java.util.ArrayList;


@Route(path = RoutMap.ACTIVITY_MAIN)
public class TestResultActivity extends BaseActivity {


    private TextView tv_result;
    private TextView tv_result_desc;
    private int data;
    private int type;//1视力 2 色盲 3散光 4颜色敏感度
    private View line;

    public static void start(Context context,int type,int data){
        Intent intent  = new Intent(context, TestResultActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("data",data);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_result;
    }


    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        tv_result = findViewById(R.id.tv_result);
        tv_result_desc = findViewById(R.id.tv_result_desc);
        line = findViewById(R.id.line);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        MaterialButton bt_restart = findViewById(R.id.bt_restart);
        bt_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        type = intent.getIntExtra("type",0);
        data = intent.getIntExtra("data",0);
        if (type==1){
            String shili = ShiLiEView2.getShiLiStr(data);
            tv_result.setText(ShiLiEView2.getShiLiStr(data));
            if (data<9){
                tv_result_desc.setText(String.format("Your vision is %s,Myopia degree is more than 650 degrees.Your vision is a bit terrible. Please wear glasses to test your corrected vision",
                        shili));
            }else if (data == 9){
                tv_result_desc.setText(String.format("Your vision is %s,Myopia degree is 650 .Your vision is a bit terrible. Please wear glasses to test your corrected vision",
                        shili));
            }else if (data == 10){
                tv_result_desc.setText(String.format("Your vision is %s,Myopia degree is 550 .Your vision is a bit terrible. Please wear glasses to test your corrected vision",
                        shili));
            }else if (data == 11){
                tv_result_desc.setText(String.format("Your vision is %s.Myopia degree is 500 .There is something wrong with your eye usage habits, please improve your eye usage habits",
                        shili));
            }else if (data == 12){
                tv_result_desc.setText(String.format("Your vision is %s.Myopia degree is 450 .There is something wrong with your eye usage habits, please improve your eye usage habits",
                        shili));
            }else if (data == 13){
                tv_result_desc.setText(String.format("Your vision is %s.Myopia degree is 400 .There is something wrong with your eye usage habits, please improve your eye usage habits",
                        shili));
            }else if (data == 14){
                tv_result_desc.setText(String.format("Your vision is %s.Myopia degree is 300 .There is something wrong with your eye usage habits, please improve your eye usage habits",
                        shili));
            }else if (data == 15){
                tv_result_desc.setText(String.format("Your vision is %s.Myopia degree is 250 .There is something wrong with your eye usage habits, please improve your eye usage habits",
                        shili));
            }else if (data == 16){
                tv_result_desc.setText(String.format("Your vision is %s.Myopia degree is 200 .There is something wrong with your eye usage habits, please improve your eye usage habits",
                        shili));
            }else if (data == 17){
                tv_result_desc.setText(String.format("Your vision is %s.Myopia degree is 150 .There is something wrong with your eye usage habits, please improve your eye usage habits",
                        shili));
            }else if (data == 18){
                tv_result_desc.setText(String.format("Your vision is %s.Myopia degree is 100 .There is something wrong with your eye usage habits, please improve your eye usage habits",
                        shili));
            }else if (data > 18){
                tv_result_desc.setText(String.format("Your vision is %s.No need to wear glasses, that’s great, please continue to maintain good eye habits, come on",
                        shili));
            }
        } else if (type==2){
            tv_result.setVisibility(View.VISIBLE);
            tv_result.setText(String.format("correct:%d",data));
            if (data==1){
                tv_result_desc.setText("You are most likely color blind");
            }else if (data==2){
                tv_result_desc.setText("You are most likely color blind");
            }else if (data==3){
                tv_result_desc.setText("You may have color blindness, please take the test several times");
            }else if (data==4){
                tv_result_desc.setText("Did you fail the test just now? Do you want to try it again?");
            }else if (data==5){
                tv_result_desc.setText("Congratulations, you have no symptoms of color blindness");
            }else if (data==0){
                tv_result_desc.setText("You are most likely color blind");
            }

        }else if (type==3){
            tv_result.setVisibility(View.VISIBLE);
            if (data==1){
                tv_result.setText("suspected");
                tv_result_desc.setText("You have symptoms of astigmatism");
            }else {
                tv_result.setText("normal");
                tv_result_desc.setText("Congratulations, you have no symptoms of astigmatism");
            }

        }else if (type==4){
            line.setVisibility(View.INVISIBLE);
            tv_result.setVisibility(View.GONE);
            tv_result_desc.setText(String.format("You have passed %d levels in total",data));
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }



}
