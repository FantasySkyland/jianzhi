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
                tv_result_desc.setText(String.format("您的视力是%s,近视度数为650度以上，, 您的视力有点惨不忍睹，请带上眼镜测试下矫正后的视力吧",
                        shili));
            }else if (data == 9){
                tv_result_desc.setText(String.format("您的视力是%s,近视度数为650, 您的视力有点惨不忍睹，请带上眼镜测试下矫正后的视力吧",
                        shili));
            }else if (data == 10){
                tv_result_desc.setText(String.format("您的视力是%s,近视度数为550, 您的视力有点惨不忍睹，请带上眼镜测试下矫正后的视力吧",
                        shili));
            }else if (data == 11){
                tv_result_desc.setText(String.format("您的视力是%s,近视度数为500, 您的用眼习惯有问题,请改进你的用眼习惯",
                        shili));
            }else if (data == 12){
                tv_result_desc.setText(String.format("您的视力是%s,近视度数为450, 您的用眼习惯有问题,请改进你的用眼习惯",
                        shili));
            }else if (data == 13){
                tv_result_desc.setText(String.format("您的视力是%s,近视度数为400, 您的用眼习惯有问题,请改进你的用眼习惯",
                        shili));
            }else if (data == 14){
                tv_result_desc.setText(String.format("您的视力是%s,近视度数为300, 您的用眼习惯有问题,请改进你的用眼习惯",
                        shili));
            }else if (data == 15){
                tv_result_desc.setText(String.format("您的视力是%s,近视度数为250, 您的用眼习惯有问题,请改进你的用眼习惯",
                        shili));
            }else if (data == 16){
                tv_result_desc.setText(String.format("您的视力是%s,近视度数为200, 您的用眼习惯有问题,请改进你的用眼习惯",
                        shili));
            }else if (data == 17){
                tv_result_desc.setText(String.format("您的视力是%s,近视度数为150, 您的用眼习惯有问题,请改进你的用眼习惯",
                        shili));
            }else if (data == 18){
                tv_result_desc.setText(String.format("您的视力是%s,近视度数为100, 您的用眼习惯有问题,请改进你的用眼习惯",
                        shili));
            }else if (data > 18){
                tv_result_desc.setText(String.format("您的视力是%s,无需佩戴眼镜, 太棒了，请您继续保持良好的用眼习惯，加油",
                        shili));
            }
        } else if (type==2){
            tv_result.setVisibility(View.VISIBLE);
            tv_result.setText(String.format("正确:%d",data));
            if (data==1){
                tv_result_desc.setText("您很大几率是色盲患者");
            }else if (data==2){
                tv_result_desc.setText("您很大几率是色盲患者");
            }else if (data==3){
                tv_result_desc.setText("您有患色盲的可能,请多测几次");
            }else if (data==4){
                tv_result_desc.setText("刚才是不是没测好，要不要再来一次");
            }else if (data==5){
                tv_result_desc.setText("恭喜您,您有没有色盲症状");
            }else if (data==0){
                tv_result_desc.setText("您很大几率是色盲患者");
            }

        }else if (type==3){
            tv_result.setVisibility(View.VISIBLE);
            if (data==1){
                tv_result.setText("疑似");
                tv_result_desc.setText("您有散光症状");
            }else {
                tv_result.setText("正常");
                tv_result_desc.setText("恭喜您,您有没有散光症状");
            }

        }else if (type==4){
            line.setVisibility(View.INVISIBLE);
            tv_result.setVisibility(View.GONE);
            tv_result_desc.setText(String.format("您总共通过了%d关",data));
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
